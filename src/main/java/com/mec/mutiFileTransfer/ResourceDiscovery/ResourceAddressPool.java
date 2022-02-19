package com.mec.mutiFileTransfer.ResourceDiscovery;

import java.util.*;

/**
 * //TODO add class commment here
 *
 * @Author wfh
 * @Date 2022/2/19 下午3:39
 */
public class ResourceAddressPool {
    /**
     * 根据一个resourceId得到资源拥有者列表
     */
    private static final Map<String, List<ResourceHolder>> resourceToHolderPool;

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

    public ResourceAddressPool() {
    }

    /**
     * 删除拥有者地址
     * @param resourceHolder
     */
    void removeAddress(ResourceHolder resourceHolder) {
        List<String> resources = holderToResourcePool.get(resourceHolder);

        if (resources == null) {
            
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
        List<ResourceHolder> holders = resourceToHolderPool.get(resourceId);

        if (holders == null) {
            /**
             * 在谁的作用域内就可以锁住谁
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

    void removeholder(String resourceId,ResourceHolder resourceHolder) {}
}
