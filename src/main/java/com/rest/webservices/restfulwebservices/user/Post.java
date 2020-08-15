package com.rest.webservices.restfulwebservices.user;

import java.util.Date;

public class Post {
    Integer id;
    private String message;
    private Date timestamp;

    public Post(Integer id, String message, Date timestamp) {
        this.id = id;
        this.message = message;
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
