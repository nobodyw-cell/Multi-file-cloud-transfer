package com.mec.mutiFileTransfer.nio.server.view;

import com.mec.mutiFileTransfer.util.IListener;
import com.mec.mutiFileTransfer.util.view.IMecView;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * //TODO add class commment here
 *
 * @Author wfh
 * @Date 2022/2/14 下午2:49
 */
public class ServerView implements IMecView, IListener {
    private JFrame jfrmServerView;
    private JTextArea jtaSystemMessage;
    private int width = 800;
    private int height = 600;


    @Override
    public void init() {
        this.jfrmServerView = new JFrame("NIO-测试 服务器端");
        this.jfrmServerView.setSize(new Dimension(width,height));
        this.jfrmServerView.setLocationRelativeTo(null);
        this.jfrmServerView.setLayout(new BorderLayout());
        this.jfrmServerView.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel jlblTopic = new JLabel("NIO-测试 服务器端",JLabel.CENTER);
        jlblTopic.setFont(topicFont);
        jlblTopic.setForeground(new Color(0xFF,0,0));
        this.jfrmServerView.add(jlblTopic,BorderLayout.NORTH);

        this.jtaSystemMessage = new JTextArea();
        this.jtaSystemMessage.setFont(normalFont);
        JScrollPane jscpSystemMessage = new JScrollPane(this.jtaSystemMessage);
        TitledBorder ttbdSystemMessage = new TitledBorder("系统信息");
    }

    @Override
    public void afterShowView() {

    }

    @Override
    public void dealAction() {
        this.jfrmServerView.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                closeView();
            }
        });
    }

    @Override
    public RootPaneContainer getFrame() {
        return this.jfrmServerView;
    }

    @Override
    public void messageFromSpeaker(String message) {

    }
}
