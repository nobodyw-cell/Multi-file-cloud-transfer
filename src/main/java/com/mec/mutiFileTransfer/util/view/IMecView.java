package com.mec.mutiFileTransfer.util.view;

import javax.swing.*;
import java.awt.*;

/**
 * //TODO add interface commment here
 *
 * @Author wfh
 * @Date 2022/2/14 下午2:57
 */
public interface IMecView {
    Font topicFont =  new Font("微软雅黑",Font.BOLD,26);
    int topicFontSize = topicFont.getSize();
    Font normalFont = new Font("宋体",Font.PLAIN,16);
    int normalFontSize = normalFont.getSize();
    Font smallFont = new Font("宋体",Font.PLAIN,12);
    int smallFontSize = smallFont.getSize();

    public void init();
    public void afterShowView();
    public void dealAction();
    public RootPaneContainer getFrame();

    default void beforeCloseView() {};
    default void afterCloseView() {};
    default IMecView initView() {
        init();
        dealAction();
        return this;
    }
    default void showView()  {
        RootPaneContainer rootPaneContainer =getFrame();

        if (rootPaneContainer instanceof Frame) {
            ((Frame) rootPaneContainer).setVisible(true);
        } else {
            ((JDialog) rootPaneContainer).setVisible(true);
        }
        afterShowView();
    }

    default void closeView() {
        RootPaneContainer rootPaneContainer = getFrame();

        beforeCloseView();
        if (rootPaneContainer instanceof Frame) {
            ((Frame) rootPaneContainer).dispose();
        } else {
            ((JDialog) rootPaneContainer).dispose();
        }
        afterCloseView();
    }
}
