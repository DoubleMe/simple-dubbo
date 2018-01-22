package com.chenhm.rpc.invoker;


import com.chenhm.base.URL;
import com.chenhm.base.exception.RpcException;
import com.chenhm.base.result.Result;
import com.chenhm.rpc.invocation.Invocation;

import java.util.Map;

/**
 * @author chen-hongmin
 * @since 2018/1/4 18:06
 */
public interface Invoker<T> {

    /**
     * get url.
     *
     * @return url.
     */
    URL getUrl();

    /**
     * is available.
     *
     * @return available.
     */
    boolean isAvailable();

    /**
     * destroy.
     */
    void destroy();

    /**
     * get service interface.
     *
     * @return service interface.
     */
    Class<T> getInterface();

    /**
     *
     * @return
     */
    Map<String, String> getAttachments();

    /**
     * invoke.
     *
     * @param invocation
     * @return result
     * @throws RpcException
     */
    Result invoke(Invocation invocation) throws RpcException;
}
