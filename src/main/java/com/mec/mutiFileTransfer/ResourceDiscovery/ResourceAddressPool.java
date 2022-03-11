package com.mec.mutiFileTransfer.ResourceDiscovery;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

/**
 * resource和address多对多双向绑定
 * 这样的话可以通过 resource找到holder 也可以通过holder找到他有那些resource0
 *
 * @Author wfh
 * @Date 2022/2/19 下午3:39
 */
public class ResourceAddressPool extends UnicastRemoteObject implements IResourceRequester{
    /**
     * 根据一个resourceId得到资源拥有者列表
     */
    private static final Map<String, ArrayList<ResourceHolder>> resourceToHolderPool;

    /**
     * 根据一个资源拥有者的地址,得到他所拥有的所有资源
     *
     * 要是将键改为ResourceHolder的hashcode会减少空间的消耗
     */
    private static final Map<ResourceHolder,List<String>> holderToResourcePool;

    static {
        holderToResourcePool = new HashMap<>();
    }

    static {
        resourceToHolderPool = new HashMap<>();
    }

    public ResourceAddressPool() throws RemoteException {
    }

    @Override
    public ArrayList<ResourceHolder> getAddressList(String resourceId) {
        return getResourceHolders(resourceId);
    }

    @Override
    public void deleteResourceRecord(ResourceHolder resourceHolder) {
        removeAddress(resourceHolder);
    }

    /**
     * 得到资源拥有者列表
     *
     * @param resourceId
     * @return
     */
    ArrayList<ResourceHolder> getResourceHolders(String resourceId) {
        return resourceToHolderPool.get(resourceId);
    }

    /**
     * 删除拥有者地址
     * @param resourceHolder
     */
    void removeAddress(ResourceHolder resourceHolder) {
        List<String> resources = holderToResourcePool.get(resourceHolder);

        if (resources == null) {
            return;
        }

        holderToResourcePool.remove(resourceHolder);

        synchronized (resourceToHolderPool) {
            for (String resource : resources) {
                List<ResourceHolder> holders = resourceToHolderPool.get(resource);

                holders.remove(resourceHolder);
            }

        }
    }

    public void RegistResourceAddress(String resourceId,ResourceHolder resourceHolder) {
        RegistAddress(resourceId,resourceHolder);
        RegistResource(resourceHolder,resourceId);
    }

    /**
     * 注册资源拥有者所拥有的资源 算是一种双向绑定
     *
     * add 为健 id为值
     *
     * @param resourceId
     * @param resourceHolder
     */
    private void RegistResource(ResourceHolder resourceHolder, String resourceId) {
        List<String> resources = holderToResourcePool.get(resourceHolder);

        if (resources == null) {
            synchronized (holderToResourcePool) {
                resources = holderToResourcePool.get(resourceHolder);

                if (resources == null) {
                    resources = new ArrayList<>();
                    holderToResourcePool.put(resourceHolder,resources);
                }
            }
        }

        resources.add(resourceId);
    }



    /**
     * 注册资源拥有者地址
     *
     * id 为健 add为值
     *
     * @param resourceId
     * @param resourceHolder
     */
    private void RegistAddress(String resourceId,ResourceHolder resourceHolder) {
        ArrayList<ResourceHolder> holders = resourceToHolderPool.get(resourceId);

        if (holders == null) {
            /**
             * synchronized里的参数实际上是获取锁的地方
             * 只有这个锁可以被获得(未被其他线程中的代码段占用)才可以进入这一代码段
             */
            synchronized (resourceToHolderPool) {
                holders = resourceToHolderPool.get(resourceId);

                if (holders == null) {
                    holders = new ArrayList<>();
                    resourceToHolderPool.put(resourceId,holders);
                }
            }
        }

        holders.add(resourceHolder);
    }

}
