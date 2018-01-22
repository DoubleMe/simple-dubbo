package com.chenhm.registy.zookeeper;

import com.chenhm.base.URL;

import java.util.List;

/**
 * @author chen-hongmin
 * @since 2018/1/5 14:48
 */
public interface ZookeeperClient {

    /**
     * 创建节点
     * @param path 节点路径
     * @param ephemeral 是否临时节点
     */
    void create(String path, boolean ephemeral);

    /**
     * 删除节点
     * @param path
     */
    void delete(String path);

    /**
     * 获取路径下子节点
     * @param path 父节点路径
     * @return 子节点列表
     */
    List<String> getChildren(String path);

    /**
     * 获取url 信息
     * @return url
     */
    URL getUrl();

    /**
     * 发现服务
     * @return
     */
    List<URL> findServers();
}
