package com.chenhm.registy.zookeeper;

import com.chenhm.base.URL;
import com.chenhm.base.UrlKeys;
import com.chenhm.base.constant.BizConstants;
import com.chenhm.base.util.UrlUtils;
import org.I0Itec.zkclient.ZkClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author chen-hongmin
 * @since 2018/1/5 14:53
 */
public abstract class AbstractClient implements ZookeeperClient {

    /**
     * zk 客户端
     */
    private ZkClient zkClient;

    /**
     * url 信息
     */
    private URL url;

    public AbstractClient(URL url) {
        this.url = url;
        zkClient = new ZkClient(url.getHost());
    }

    @Override
    public void create(String path, boolean ephemeral) {

        boolean exists = zkClient.exists(path);
        if (exists) {
            return;
        }
        if (ephemeral) {
            zkClient.createEphemeral(path);
        } else {
            zkClient.createPersistent(path);
        }
    }

    @Override
    public void delete(String path) {
        zkClient.delete(path);
    }

    @Override
    public List<String> getChildren(String path) {

        List<String> children = zkClient.getChildren(path);
        return children;
    }

    @Override
    public URL getUrl() {
        return this.url;
    }

    @Override
    public List<URL> findServers() {


        StringBuilder path = new StringBuilder(BizConstants.ROOT);
        path.append("/").append(url.getParameter(UrlKeys.INTERFACE)).append("/").append(BizConstants.PROVIDER);
        List<URL> urls = new ArrayList<>();

        List<String> children = zkClient.getChildren(path.toString());

        if (children == null || children.isEmpty()) {
            return urls;
        }

        for (String child : children) {

            Map<String, String> parameters = UrlUtils.getParameters(child);
            URL serverUrl = new URL(url.getHost(), url.getParameter(UrlKeys.PROTOCOL), parameters);
            if (checkServer(serverUrl)) {
                urls.add(serverUrl);
            }
        }
        return urls;
    }

    private boolean checkServer(URL serverUrl) {

        String group = url.getParameter(UrlKeys.GROUP);
        String version = url.getParameter(UrlKeys.VERSION);

        if (group != null && group.equals(serverUrl.getParameter(UrlKeys.GROUP))) {
            return false;
        }

        if (version != null && version.equals(serverUrl.getParameter(UrlKeys.GROUP))) {
            return false;
        }

        return true;
    }
}
