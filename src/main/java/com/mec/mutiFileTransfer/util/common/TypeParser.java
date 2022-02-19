package com.mec.mutiFileTransfer.util.common;

import org.junit.Test;

/**
 * //TODO add class commment here
 *
 * @Author wfh
 * @Date 2022/2/2 下午3:34
 */
public class TypeParser {

    /**
     * 将整型int转换为byte数组
     *
     * @author wfh
     * @date 下午3:40 2022/2/2
     * @param val
     * @return byte[]
     **/
    public static byte[] toBytes(int val) {
        byte[] bytes = new byte[4];

        for (int i = 0; i < 4; i++) {
            bytes[i] = (byte) ((val >> (8 * i)) & 0xFF);
        }

        return bytes;
    }

    public static byte[] toBytes(long val) {
        byte[] bytes = new byte[8];

        for (int i = 0; i < 8; i++) {
            bytes[i] = (byte) ((val >> (8 * i)) & 0xFF);
        }

        return bytes;
    }

    public static int bytesToInt(byte[] bytes) {
        int ans = 0;

        for (int i = 3; i >= 0; i--) {
            ans<<=8;
            ans |= (bytes[i] & 0xFF);
        }

        return ans;
    }


    public static int bytesToLong(byte[] bytes) {
        int ans = 0;

        for (int i = 7; i >= 0; i--) {
            ans<<=8;
            ans |= (bytes[i] & 0xFF);
        }

        return ans;
    }

    public static void setByteAt(byte[] target,int offset,byte[] bytes) {
        for (int i = offset; i < offset + bytes.length; i++) {
            target[i] = bytes[i-offset];
        }
    }

    public static byte[] subBytes(byte[] bytes,int offset,int len) {
        byte[] ans = new byte[len];

        for (int i = 0; i < len; i++) {
            ans[i] = bytes[offset + i];
        }

        return ans;
    }
}
