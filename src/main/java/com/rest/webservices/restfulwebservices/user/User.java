package com.rest.webservices.restfulwebservices.user;

import com.fasterxml.jackson.annotation.JsonFilter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;

@ApiModel(description = "User model")
public class User {
    private Integer id;
    @ApiModelProperty(notes = "Name should have at least 2 characters")
    @Size(min = 2, message = "Name should have at least 2 characters")
    private String name;
    @ApiModelProperty(notes = "Birth date should be in the past")
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
