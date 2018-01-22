package com.chenhm.base.result;

import java.io.Serializable;

/**
 * @author chen-hongmin
 * @since 2018/1/10 15:04
 */
public class RpcResult implements Result, Serializable {

    private static final long serialVersionUID = -6925924956850004727L;

    private Object result;

    private Throwable exception;

    public RpcResult() {

    }

    @Override
    public Object getValue() {
        return result;
    }

    @Override
    public Throwable getException() {
        return exception;
    }

    @Override
    public boolean hasException() {
        return exception != null;
    }
    @Override
    public Object recreate() throws Throwable {
        if (exception != null) {
            throw exception;
        }
        return result;
    }


    public void setObject(Object object) {
        this.result = object;
    }

    public void setException(Throwable exception) {
        this.exception = exception;
    }
}
