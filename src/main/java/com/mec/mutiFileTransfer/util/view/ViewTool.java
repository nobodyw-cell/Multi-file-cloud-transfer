package com.mec.mutiFileTransfer.util.view;

import javax.swing.*;

/**
 * //TODO add class commment here
 *
 * @Author wfh
 * @Date 2022/2/14 下午8:18
 */
public class ViewTool {
    public static boolean getUserChoice(JFrame parentView,String message) {
        int userChoice = JOptionPane.showConfirmDialog(parentView, message,
                "wang", JOptionPane.YES_NO_OPTION);
        return userChoice == JOptionPane.YES_OPTION;
    }

}
