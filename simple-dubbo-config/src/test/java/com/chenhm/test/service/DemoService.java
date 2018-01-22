package com.chenhm.test.service;

import com.chenhm.test.service.dto.MessageDTO;

/**
 * @author chen-hongmin
 * @since 2018/1/11 14:53
 */
public interface DemoService {

    String sendMessage(String message);

    String sendMessage(MessageDTO messageDTO);
}
