package com.chenhm.rpc.proxy;

import com.chenhm.base.URL;
import com.chenhm.base.exception.RpcException;
import com.chenhm.rpc.invocation.impl.RpcInvocationHandler;
import com.chenhm.rpc.invoker.Invoker;
import com.chenhm.rpc.invoker.impl.JavassistInvoker;

/**
 * @author chen-hongmin
 * @since 2018/1/15 11:27
 */
public class JavassistProxy implements ProxyFactory {

    @Override
    public <T> T getProxy(Invoker<T> invoker, Class<T> clazz) throws RpcException {
        return (T) Proxy.getProxy(clazz).newInstance(new RpcInvocationHandler(invoker));

    }

    @Override
    public <T> Invoker<T> getInvoker(T proxy, Class<T> type, URL url) throws RpcException {

        return new JavassistInvoker(proxy, type, url);
    }
}
