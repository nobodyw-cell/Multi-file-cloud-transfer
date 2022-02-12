package com.mec.mutiFileTransfer.ResourceDiscovery;

/**
 * //TODO add class commment here
 *
 * @Author wfh
 * @Date 2022/2/11 下午8:48
 */
public class ResourceHolder implements INodeAddress{
    private String ip;
    private int port;

    private boolean canSend;
    private int sendedCount;
    private int sendingCount;

    public boolean beforeSend() {
        if (this.canSend == true) {
            this.sendingCount++;
            return true;
        }

        return false;
    }

    public int getSendedCount() {
        return sendedCount;
    }

    public int getSendingCount() {
        return sendingCount;
    }

    public boolean isCanSend() {
        return this.canSend;
    }


    public void afterSend() {
        this.sendingCount--;
        this.sendedCount++;
    }

    @Override
    public void setIp(String ip) {
        this.ip = ip;
    }

    @Override
    public void setPort(int port) {
        this.port = port;
    }

    @Override
    public int getPort() {
        return this.port;
    }

    @Override
    public String getIp() {
        return this.ip;
    }

    @Override
    public String toString() {
        return "ResourceHolder{" +
                "ip='" + ip + '\'' +
                ", port=" + port +
                ", canSend=" + canSend +
                ", sendedCount=" + sendedCount +
                ", sendingCount=" + sendingCount +
                '}';
    }
}
