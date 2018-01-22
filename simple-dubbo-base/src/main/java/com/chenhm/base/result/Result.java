package com.chenhm.base.result;

/**
 * @author chen-hongmin
 * @since 2018/1/5 15:23
 */
public interface Result {

    /**
     * Get invoke result.
     *
     * @return result. if no result return null.
     */
    Object getValue();

    /**
     * Get exception.
     *
     * @return exception. if no exception return null.
     */
    Throwable getException();

    /**
     * Has exception.
     *
     * @return has exception.
     */
    boolean hasException();

    Object recreate() throws Throwable;
}
