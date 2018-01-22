package com.chenhm.rpc.rpc;

import java.io.Serializable;

/**
 * @author chen-hongmin
 * @since 2018/1/11 9:34
 */
public class Response implements Serializable{


    /**
     * 返回值
     */
    private Object value;

    /**
     * 返回状态
     */
    private ResponseStatus status;

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public ResponseStatus getStatus() {
        return status;
    }

    public void setStatus(ResponseStatus status) {
        this.status = status;
    }
}
