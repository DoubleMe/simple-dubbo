package com.chenhm.test.service.impl;

import com.chenhm.test.service.DemoService;
import com.chenhm.test.service.dto.MessageDTO;

/**
 * @author chen-hongmin
 * @since 2018/1/11 14:57
 */
public class DemoServiceImpl implements DemoService {

    @Override
    public String sendMessage(String message) {

        System.out.println("接受的消息:" + message);
        return "给你消息" + message;
    }

    @Override
    public String sendMessage(MessageDTO messageDTO) {
        return "来自服务端给你的问候";
    }
}
