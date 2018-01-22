package com.chenhm.base.cache;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author chen-hongmin
 * @since 2018/1/15 14:42
 */
public abstract class AbstractCache<V> implements Cache<V> {

    private static ConcurrentHashMap cache = new ConcurrentHashMap<>();



    @Override
    public void put(V v) {
        Object key = getKey(v);

        cache.put(key, v);

    }

    @Override
    public <K> void put(K k, V v) {
        Object key = getKey(k);
        cache.put(key, v);
    }

    @Override
    public <K> V get(K k) {

        if (cache.containsKey(k)){
            return (V) cache.get(k);
        }
        return null;
    }

    public abstract class KeyGenerator<K> {

        /**
         * 生成key
         *
         * @param k
         * @return
         */
        protected abstract Object generateKey(K k);

    }

    /**
     * 获取key
     * @param k
     * @param <K>
     * @return
     */
    private <K> Object getKey(K k){
        KeyGenerator keyGenerator = getKeyGenerator();
        if (keyGenerator == null){
            return k;
        }

        return keyGenerator.generateKey(k);
    }

    /**
     * 获取KeyGenerator
     * @return
     */
    protected abstract KeyGenerator getKeyGenerator();


}
