package com.mec.mutiFileTransfer.nio.client.view;

import com.mec.mutiFileTransfer.util.nio.client.NioClient;
import com.mec.mutiFileTransfer.util.nio.client.NioClientConversation;
import com.mec.mutiFileTransfer.util.view.IMecView;
import com.mec.mutiFileTransfer.util.view.ViewTool;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * //TODO add class commment here
 *
 * @Author wfh
 * @Date 2022/2/14 下午7:32
 */
public class ConnecttedToServerView implements IMecView{
    private static final String connectTip = "第#次连接...";
    private JFrame jfrmConnect;
    private JLabel jlblContent;
    private NioClient client;
    private int connectTimes;
    private int height = 200;
    private int width = 400;


    public ConnecttedToServerView() {
        this.connectTimes = 0;
        this.client = new NioClient();
        this.client.setIp("127.0.0.1");
        this.client.setPort(54188);
        this.client.setProcessor(new NioClientConversation() {
            @Override
            public void dealMessage(byte[] message) {
            }

            @Override
            public void peerAbnormalDrop() {
                System.out.println("服务器异常宕机");
            }
        });
        initView();
    }

    @Override
    public void init() {
        this.jfrmConnect = new JFrame("connectToServer");
        this.jfrmConnect.setSize(width,height);
        this.jfrmConnect.setResizable(false);
        this.jfrmConnect.setLocationRelativeTo(null);
        this.jfrmConnect.setLayout(new BorderLayout());
        this.jfrmConnect.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        JLabel jlblTopic = new JLabel("连接到服务器",JLabel.CENTER);
        jlblTopic.setFont(topicFont);
        jlblTopic.setForeground(new Color(221, 5, 246));
        this.jfrmConnect.add(jlblTopic,BorderLayout.NORTH);

        this.jlblContent = new JLabel();
        this.jlblContent.setFont(normalFont);
        this.jfrmConnect.add(jlblContent,BorderLayout.CENTER);
    }

    @Override
    public void afterShowView() {
        boolean ok = true;
        while (ok) {
            this.jlblContent.setText(connectTip.replace("#",String.valueOf(++this.connectTimes)));
            try {
                this.client.connectToServer();
                ok = false;
            } catch (IOException e) {
                ok = ViewTool.getUserChoice(this.jfrmConnect,"还要继续连接吗");
                if (ok == false) {
                    closeView();
                }
            }
        }
    }

    @Override
    public void dealAction() {

    }

    @Override
    public RootPaneContainer getFrame() {
        return this.jfrmConnect;
    }

    @Override
    public boolean beforeCloseView() {
        return true;
    }

    @Override
    public void afterCloseView() {
        this.client.close();
    }

    @Override
    public void beforeShowView() {

    }
}
