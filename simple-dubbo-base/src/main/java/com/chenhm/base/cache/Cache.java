package com.chenhm.base.cache;

/**
 * @author chen-hongmin
 * @since 2018/1/15 14:39
 */
public interface Cache<V> {

    /**
     * 添加缓存对象
     * @param v
     */
    void put(V v);

    /**
     * 添加缓存对象
     * @param k
     * @param v
     * @param <K>
     */
    <K>void put(K k, V v);

    /**
     * 获取缓存对象
     * @param k
     * @return
     */
    <K> V get(K k);

}
