package com.rest.webservices.restfulwebservices.user;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
@Entity
public class Post {
    @Id
    @GeneratedValue
    Integer id;
    private String message;
    private Date timestamp;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonIgnore
    private User users;

    public Post(Integer id, String message, Date timestamp) {
        this.id = id;
        this.message = message;
        this.timestamp = timestamp;
    }

    public Post() {

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



    public User getUsers() {
        return users;
    }

    public void setUsers(User users) {
        this.users = users;
    }
}
