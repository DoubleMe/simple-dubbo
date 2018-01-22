package com.chenhm.rpc.invocation.impl;


import com.chenhm.rpc.invoker.Invoker;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author chen-hongmin
 * @since 2018/1/6 16:43
 */
public class RpcInvocationHandler implements InvocationHandler {

    private Invoker invoker;

    public RpcInvocationHandler(Invoker invoker) {
        this.invoker = invoker;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        if (proxy.getClass().equals(Object.class)) {
            method.invoke(proxy, args);
        }
        return invoker.invoke(new RpcInvocation(method, args)).recreate();
    }

}
