package com.chenhm.rpc.invocation.impl;

import com.chenhm.rpc.invocation.Invocation;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author chen-hongmin
 * @since 2018/1/10 14:08
 */
public class RpcInvocation implements Invocation, Serializable {

    /**
     * 方法名称
     */
    private String methodName;

    /**
     * 参数类型
     */
    private Class<?>[] parameterTypes;

    /**
     * 参数数组
     */
    private Object[] arguments;

    /**
     * 参数map
     */
    private Map<String, String> attachments;

    public RpcInvocation(Method method, Object[] arguments) {

        this(method.getName(), method.getParameterTypes(), arguments, new HashMap<>());
    }

    public RpcInvocation(String methodName, Class<?>[] parameterTypes, Object[] arguments) {

        this(methodName, parameterTypes, arguments, new HashMap<>());
    }

    public RpcInvocation(String methodName, Class<?>[] parameterTypes, Object[] arguments, Map<String, String> attachments) {
        this.methodName = methodName;
        this.parameterTypes = parameterTypes;
        this.arguments = arguments;
        this.attachments = attachments;
    }

    @Override
    public String getMethodName() {
        return methodName;
    }

    @Override
    public Class<?>[] getParameterTypes() {
        return parameterTypes;
    }

    @Override
    public Object[] getArguments() {
        return arguments;
    }

    @Override
    public Map<String, String> getAttachments() {
        return attachments;
    }

    @Override
    public String getAttachment(String key) {
        return attachments.get(key);
    }

    @Override
    public String getAttachment(String key, String defaultValue) {
        if (attachments.containsKey(key)) {
            return getAttachment(key);
        }
        return defaultValue;
    }

    @Override
    public void addAttachment(String key, String value) {
        attachments.put(key, value);
    }
}
