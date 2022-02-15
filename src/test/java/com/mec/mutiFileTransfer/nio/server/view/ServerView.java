package com.mec.mutiFileTransfer.nio.server.view;

import com.mec.mutiFileTransfer.util.IListener;
import com.mec.mutiFileTransfer.util.nio.common.INetMessageProcessor;
import com.mec.mutiFileTransfer.util.nio.server.NioServer;
import com.mec.mutiFileTransfer.util.view.IMecView;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

/**
 * //TODO add class commment here
 *
 * @Author wfh
 * @Date 2022/2/14 下午2:49
 */
public class ServerView implements IMecView, IListener {


    private JFrame jfrmServerView;
    private JTextArea jtaSystemMessage;
    private JTextField jtfCommand;
    private int width = 800;
    private int height = 600;

    private NioServer server;

    public ServerView() {
        this.server = new NioServer();
        this.server.addListenner(this);
        this.server.setProcessor(new INetMessageProcessor() {
            @Override
            public void dealNetMessage(byte[] message) {
                String mess = new String(message);
                server.speak(mess);
            }

            @Override
            public void peerAbnormalDrop() {
            }
        });
        initView();
    }

    @Override
    public void init() {
        this.jfrmServerView = new JFrame("NIO-测试 服务器端");
        this.jfrmServerView.setSize(new Dimension(width,height));
        this.jfrmServerView.setLocationRelativeTo(null);
        this.jfrmServerView.setLayout(new BorderLayout());
        this.jfrmServerView.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel jlblTopic = new JLabel("terminal",JLabel.CENTER);
        jlblTopic.setFont(topicFont);
        jlblTopic.setForeground(new Color(255, 0, 132));
        this.jfrmServerView.add(jlblTopic,BorderLayout.NORTH);

        this.jtaSystemMessage = new JTextArea();
        this.jtaSystemMessage.setFont(normalFont);
        JScrollPane jscpSystemMessage = new JScrollPane(this.jtaSystemMessage);
        TitledBorder ttbdSystemMessage = new TitledBorder("系统信息");
        ttbdSystemMessage.setTitleFont(normalFont);
        ttbdSystemMessage.setTitleColor(Color.RED);
        ttbdSystemMessage.setTitlePosition(TitledBorder.TOP);
        ttbdSystemMessage.setTitleJustification(TitledBorder.CENTER);
        jscpSystemMessage.setBorder(ttbdSystemMessage);
        this.jfrmServerView.add(jscpSystemMessage,BorderLayout.CENTER);

        JPanel jpnlCommand = new JPanel(new FlowLayout(FlowLayout.CENTER));
        this.jfrmServerView.add(jpnlCommand,BorderLayout.SOUTH);

        JLabel jlblCommand = new JLabel("命令");
        jlblCommand.setFont(normalFont);
        jpnlCommand.add(jlblCommand);

        this.jtfCommand = new JTextField(20);
        this.jtfCommand.setFont(normalFont);
        jpnlCommand.add(jtfCommand);
    }

    @Override
    public void afterShowView() {
        this.jtaSystemMessage.setEditable(false);
        this.jtaSystemMessage.setFocusable(false);
        this.jtfCommand.setEditable(true);
    }

    @Override
    public void dealAction() {
        this.jfrmServerView.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                closeView();
            }
        });

        this.jtfCommand.addActionListener(new ActionListener() {
            /**
             * 回车的时候会触发
             * @param actionEvent
             */
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String command = jtfCommand.getText().trim();
                dealCommand(command);
                jtfCommand.setText("");
            }
        });
    }

    public void dealCommand(String command) {
        if (command.equals("exit")
            || command.equals("x")
            || command.equals("关闭")) {
            closeView();
        } else if (command.equals("startUp")
            || command.equals("yahu")) {
            try {
                this.server.startUp();
            } catch (IOException e) {
                this.server.speak(e.getMessage());
            }
        } else if (command.equals("shutDown")) {
            this.server.shutDown();
        } else {
            this.server.speak("命令不可识别");
        }
    }


    @Override
    public RootPaneContainer getFrame() {
        return this.jfrmServerView;
    }

    @Override
    public boolean beforeCloseView() {
        if (this.server.isStartUp() == true) {
           this.server.speak("服务器尚未关闭,不能关闭监视器");
            return false;
        }
        return true;
    }

    @Override
    public void afterCloseView() {
    }

    @Override
    public void beforeShowView() {

    }

    @Override
    public void messageFromSpeaker(String message) {
        this.jtaSystemMessage.append('\n' + message);
    }
}
