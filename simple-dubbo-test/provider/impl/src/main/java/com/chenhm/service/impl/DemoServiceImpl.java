package com.chenhm.service.impl;

import com.chenhm.service.DemoService;

/**
 * @author chen-hongmin
 * @since 2018/1/22 15:45
 */
public class DemoServiceImpl implements DemoService {

    @Override
    public String sendMessage(String message) {

        System.out.println(message);
        return "服务端处理了你的消息 ->" + message;
    }
}
