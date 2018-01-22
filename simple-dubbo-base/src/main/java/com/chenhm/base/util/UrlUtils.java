package com.chenhm.base.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chen-hongmin
 * @since 2018/1/5 15:45
 */
public class UrlUtils {


    /**
     * 获取正确地址
     *
     * @param address
     * @return
     */
    public static String getAddress(String address) {

        int i = address.indexOf("://");
        if (i != -1) {
            address = address.substring(i + 3, address.length());
        }

        return address;
    }


    /**
     * 获取协议
     *
     * @param address
     * @return
     */
    public static String getProtocol(String address) {

        int i = address.indexOf("://");
        if (i != -1) {
            return address.substring(0, i);
        }

        return "";
    }

    /**
     * 获取协议
     *
     * @param host
     * @return
     */
    public static int getPort(String host) {

        String port = "";
        int i = host.indexOf("://");
        if (i != -1) {
            host = host.substring(i + 3, host.length());
        }

        int j = host.indexOf(":");
        if (j != -1) {
            port = host.substring(j + 1, host.length());
        }

        return Integer.parseInt(port);
    }

    /**
     * 获取协议
     *
     * @param host
     * @return
     */
    public static String getIp(String host) {

        String ip = "";
        int i = host.indexOf("://");
        if (i != -1) {
            host = host.substring(i + 3, host.length());
        }

        int j = host.indexOf(":");
        if (j != -1) {
            ip = host.substring(0, j);
        }

        return ip;
    }

    public static Map<String, String> getParameters(String url) {

        Map<String, String> map = new HashMap<>();

        String[] split = url.split("&");
        for (String pair : split) {
            int i = pair.indexOf("=");
            if (-1 == i) {
                continue;
            }
            String key = pair.substring(0, i);
            String value = pair.substring(i + 1, pair.length());

            map.put(key, value);
        }

        return map;
    }


    public static void main(String[] args) {
        String address = getProtocol("zookeeper://192.168.142.132:2181");

        String url = "application=dubbo-demo&version=1.0.0&interface=com.chenhm.test.service.DemoService&group=local&methods=sendMessage,wait,wait,wait,equals,toString,hashCode,getClass,notify,notifyAll,&address=192.168.142.1:21817&timestamp=null";

        Map<String, String> parameters = getParameters(url);
        System.out.println(parameters);
//        int port = getPort("zookeeper://192.168.142.132:2181");
//        String ip = getIp("zookeeper://192.168.142.132:2181");
//
//        System.out.println(address);
//        System.out.println(port);
//        System.out.println(ip);
    }
}
