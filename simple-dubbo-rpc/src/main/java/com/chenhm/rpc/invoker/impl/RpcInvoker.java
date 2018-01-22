package com.chenhm.rpc.invoker.impl;


import com.chenhm.base.URL;
import com.chenhm.base.UrlKeys;
import com.chenhm.rpc.invocation.Invocation;
import com.chenhm.rpc.invoker.AbstractInvoker;
import com.chenhm.base.result.Result;
import com.chenhm.base.util.StringUtils;
import com.chenhm.rpc.client.impl.NettyClient;
import com.chenhm.rpc.rpc.Request;

import java.util.Map;

/**
 * @author chen-hongmin
 * @since 2018/1/5 15:17
 */
public class RpcInvoker<T> extends AbstractInvoker {


    public RpcInvoker(Class<T> type, URL url, Map<String, String> attachment) {
        super(type, url, attachment);
    }

    @Override
    protected Result doInvoke(Invocation invocation) throws Throwable {

        getUrl().addParameter(UrlKeys.INTERFACE, getInterface().getName());
        getUrl().addParameter(UrlKeys.METHOD, invocation.getMethodName());

        Map<String, String> attachments = getAttachments();

        StringBuilder sb = new StringBuilder(getInterface().getName());
        if (StringUtils.isNotBlank(attachments.get(UrlKeys.VERSION))) {
            sb.append(":").append(attachments.get(UrlKeys.VERSION));
        }

        if (StringUtils.isNotBlank(attachments.get(UrlKeys.GROUP))) {
            sb.append(":").append(attachments.get(UrlKeys.GROUP));
        }
        invocation.addAttachment(UrlKeys.PATH, sb.toString());
        invocation.addAttachment(UrlKeys.INTERFACE, getInterface().getName());

        NettyClient client = new NettyClient(getUrl());
        client.open();

        Request request = new Request(getUrl().getProtocol(),invocation);

        return (Result) client.send(request).get();
    }
}
