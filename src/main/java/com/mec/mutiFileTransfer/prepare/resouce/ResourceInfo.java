package com.mec.mutiFileTransfer.prepare.resouce;

import java.util.Objects;

/**
 * 对resource的描述
 *
 * 由资源的以下部分构成
 * 1. 资源id
 * 2. 资源头部
 * 3. 资源简介
 * 4. 资源详细介绍
 *
 * @Author wfh
 * @Date 2022/2/3 下午9:55
 */
public class ResourceInfo {
    /**
     * 编号前缀可以是APP名称,资源类型名称等
     */
    private String resourceHead;
    /**
     * 资源编号
     * 由App自行产生,比如视频文件编号等等.
     */
    private String id;
    /**
     * 资源简介
     */
    private String introduce;
    /**
     * 资源详细介绍
     */
    private String declearation;

    public void setId(String id) {
        this.id = id;
    }

    public void setResourceHead(String resourceHead) {
        this.resourceHead = resourceHead;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getDeclearation() {
        return declearation;
    }

    public void setDeclearation(String declearation) {
        this.declearation = declearation;
    }

    public String getResourceId() {
        return this.resourceHead + this.id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResourceInfo that = (ResourceInfo) o;
        return id.equals(that.id) && introduce.equals(that.introduce);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, introduce);
    }

    @Override
    public String toString() {
        return this.resourceHead + this.id;
    }
}
