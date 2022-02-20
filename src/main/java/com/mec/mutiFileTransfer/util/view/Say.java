package com.mec.mutiFileTransfer.util.view;

import com.mec.mutiFileTransfer.util.view.ISay;

/**
 * //TODO add class commment here
 *
 * @Author wfh
 * @Date 2022/2/20 下午3:49
 */
public class Say implements ISay {

    @Override
    public String say() {
        String str = "调用到了";
        System.out.println(str);
        return str;
    }
}
