package com.mec.mutiFileTransfer.nio.client;

import com.mec.mutiFileTransfer.nio.client.view.ConnecttedToServerView;

/**
 * //TODO add class commment here
 *
 * @Author wfh
 * @Date 2022/2/14 下午7:51
 */
public class ClientMain {
    public static void main(String[] args) {
        ConnecttedToServerView view = new ConnecttedToServerView();

        view.showView();
    }
}
