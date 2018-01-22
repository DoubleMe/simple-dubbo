package com.chenhm.rpc.invoker;


import com.chenhm.base.URL;
import com.chenhm.base.result.Result;
import com.chenhm.base.result.RpcResult;
import com.chenhm.rpc.invocation.Invocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author chen-hongmin
 * @since 2018/1/5 9:46
 */
public abstract class AbstractInvoker<T> implements Invoker {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     *
     */
    private final Class<T> type;

    /***
     *
     */
    private final URL url;

    /**
     *
     */
    private final Map<String, String> attachment;

    /**
     *
     */
    private volatile boolean available = true;


    /**
     *
     */
    private AtomicBoolean destroyed = new AtomicBoolean(false);



    public AbstractInvoker(Class<T> type, URL url, Map<String, String> attachment) {
        this.type = type;
        this.url = url;
        this.attachment = attachment;
    }

    @Override
    public Map<String, String> getAttachments() {
        return attachment;
    }

    @Override
    public Class<T> getInterface() {
        return type;
    }

    @Override
    public URL getUrl() {
        return url;
    }

    @Override
    public boolean isAvailable() {
        return available;
    }

    protected void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public void destroy() {
        if (!destroyed.compareAndSet(false, true)) {
            return;
        }
        setAvailable(false);
    }

    public boolean isDestroyed() {
        return destroyed.get();
    }

    @Override
    public String toString() {
        return getInterface() + " -> " + (getUrl() == null ? "" : getUrl().toString());
    }

    @Override
    public Result invoke(Invocation invocation) {

        try {
            return doInvoke(invocation);
        } catch (Throwable e) { // biz exception
            e.printStackTrace();
            RpcResult rpcResult = new RpcResult();
            rpcResult.setException(e);
            return rpcResult;
        }
    }

    protected abstract Result doInvoke(Invocation invocation) throws Throwable;
}
