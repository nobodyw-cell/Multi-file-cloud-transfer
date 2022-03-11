package com.mec.mutiFileTransfer.Rmi;

import com.mec.mutiFileTransfer.ResourceDiscovery.ResourceRegistryCenter;

import java.util.ArrayList;

/**
 * //TODO add class commment here
 *
 * @Author wfh
 * @Date 2022/3/11 上午11:48
 */
public class CenterTest {
    public static void main(String[] args) {
        ResourceRegistryCenter resourceRegistryCenter = new ResourceRegistryCenter();
        resourceRegistryCenter.startUp();
    }
}
