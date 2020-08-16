package com.rest.webservices.restfulwebservices.user;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;

public class User {
    private Integer id;
    @Size(min = 2, message = "Name should have at least 2 characters")
    private String name;
    @Past
    private Date birthDate;
    private ArrayList<Post> posts;

    public User(int id,String name, Date birthDate) {
        this.id =id;
        this.name = name;
        this.birthDate = birthDate;
        this.posts = new ArrayList<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public ArrayList<Post> getPosts() {
        return posts;
    }

    public void setPosts(ArrayList<Post> posts) {
        this.posts = posts;
    }
}
