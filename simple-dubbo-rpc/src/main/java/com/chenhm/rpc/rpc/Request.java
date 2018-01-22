package com.chenhm.rpc.rpc;


import com.chenhm.rpc.invocation.Invocation;

import java.io.Serializable;

/**
 * @author chen-hongmin
 * @since 2018/1/11 9:34
 */
public class Request implements Serializable{

    public static final String VERSION = "1.0.0";

    /**
     * 请求协议
     */
    private String protocol;

    /**
     * 请求版本
     */
    private String version;

    /**
     * 请求内容
     */
    private Invocation invocation;

    public Request() {
    }

    public Request(String protocol, Invocation invocation) {
        this(protocol,VERSION,invocation);
    }

    public Request(String protocol, String version, Invocation invocation) {
        this.protocol = protocol;
        this.version = version;
        this.invocation = invocation;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Invocation getInvocation() {
        return invocation;
    }

    public void setInvocation(Invocation invocation) {
        this.invocation = invocation;
    }
}
