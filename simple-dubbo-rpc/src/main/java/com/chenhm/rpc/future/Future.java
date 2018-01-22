package com.chenhm.rpc.future;

import com.chenhm.base.exception.RpcException;

/**
 * @author chen-hongmin
 * @since 2018/1/16 14:11
 */
public interface Future {

    /**
     * get result.
     *
     * @throws RpcException
     * @return result.
     */
    Object get() throws RpcException;

    /**
     * get result with the specified timeout.
     *
     * @param timeout timeout.
     * @throws RpcException
     * @return result.
     */
    Object get(long timeout) throws RpcException;

    /**
     * check is done.
     *
     * @return done or not.
     */
    boolean isDone();
}
