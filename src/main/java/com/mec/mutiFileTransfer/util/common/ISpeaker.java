package com.mec.mutiFileTransfer.util.common;

import com.mec.mutiFileTransfer.util.common.IListener;

/**
 * //TODO add interface commment here
 *
 * @Author wfh
 * @Date 2022/2/12 下午1:35
 */
public interface ISpeaker {
    public void speak(String message);
    public void addListenner(IListener listener);
    public void removeListenner(IListener listener);
}
