package com.chenhm.test.service.dto;

import java.io.Serializable;

/**
 * @author chen-hongmin
 * @since 2018/1/16 16:10
 */
public class MessageDTO implements Serializable{

    private String message;

    private String name;

    private String id;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
