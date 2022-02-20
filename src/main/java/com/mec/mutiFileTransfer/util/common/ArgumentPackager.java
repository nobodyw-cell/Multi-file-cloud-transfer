package com.mec.mutiFileTransfer.util.common;

import com.google.gson.Gson;
import com.mec.mutiFileTransfer.util.view.Say;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * //TODO add class commment here
 *
 * @Author wfh
 * @Date 2022/2/20 下午2:19
 */
public class ArgumentPackager implements Serializable {
    public static final Gson gson = new Gson();
    private Method method;
    private Object object;
    private Object[] objects;

    public ArgumentPackager(Object o,Method method,Object[] objects) {
        this.object = new Say();
        this.method = method;
        this.objects = objects;
    }

    public String toGsonString() {
        return gson.toJson(this);
    }

    public Method getMethod() {
        return method;
    }

    public Object[] getObjects() {
        return objects;
    }

    public Object getObject() {
        return this.object;
    }

    @Override
    public String toString() {
        return "ArgumentPackager{" +
                "method=" + method.getName() +
                ", objects=" + Arrays.toString(objects) +
                '}';
    }
}
