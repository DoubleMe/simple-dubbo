package com.chenhm.rpc.proxy;

import com.chenhm.base.URL;
import com.chenhm.base.exception.RpcException;
import com.chenhm.rpc.invoker.Invoker;

/**
 * @author chen-hongmin
 * @since 2018/1/15 11:22
 */
public interface ProxyFactory {

    /**
     * create proxy.
     *
     * @param invoker
     * @param clazz
     * @return proxy
     *
     * @throws RpcException
     */
    <T> T getProxy(Invoker<T> invoker, Class<T> clazz) throws RpcException;

    /**
     * create invoker.
     *
     * @param <T>
     * @param proxy
     * @param type
     * @param url
     * @return invoker
     * @throws RpcException
     */
    <T> Invoker<T> getInvoker(T proxy, Class<T> type, URL url) throws RpcException;
}
