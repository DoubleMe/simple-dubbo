package com.chenhm.rpc.invoker.impl;


import com.chenhm.base.URL;
import com.chenhm.rpc.invocation.Invocation;
import com.chenhm.rpc.invoker.AbstractInvoker;
import com.chenhm.base.result.Result;
import com.chenhm.base.result.RpcResult;

import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * @author chen-hongmin
 * @since 2018/1/5 15:17
 */
public class JavassistInvoker<T> extends AbstractInvoker {

    private T proxy;


    public JavassistInvoker(T proxy,Class<T> type, URL url) {
        super(type, url, new HashMap<>());
        this.proxy = proxy;
    }

    @Override
    protected Result doInvoke(Invocation invocation) throws Throwable {

        Method method = getInterface().getMethod(invocation.getMethodName(), invocation.getParameterTypes());

        RpcResult rpcResult = new RpcResult();
        try {
            Object invoke = method.invoke(proxy, invocation.getArguments());
            rpcResult.setObject(invoke);
        }catch (Throwable throwable){

            throwable.printStackTrace();
            rpcResult.setException(throwable);
        }

        return rpcResult;
    }
}
