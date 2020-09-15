package com.rest.webservices.restfulwebservices.user;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
public class UserResource {
    @Autowired
    private UserDaoService service;

    @GetMapping("/users")
    public List<User> findAll(){
        return service.findAll();
    }

    @GetMapping("/users/{id}")
    public EntityModel<MappingJacksonValue> findOne(@PathVariable Integer id){
        User user = service.findOne(id);
        if (user == null){
            throw new NotFoundException("User Not Found id " + id);
        }
        //Filter ID
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id");
        FilterProvider filterProvider = new SimpleFilterProvider()
                                            .addFilter("UserFilter",filter);
        MappingJacksonValue value = new MappingJacksonValue(user);
        value.setFilters(filterProvider);
        EntityModel <MappingJacksonValue> entityModel = EntityModel.of(value);

        WebMvcLinkBuilder linkTo =
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder
                        .methodOn(this.getClass()).findAll());

        entityModel.add(linkTo.withRel("all-users"));
        return entityModel;
    }

    @PostMapping("/users")
    public ResponseEntity<Object> saveUser(@Validated @RequestBody User user){
        user = service.save(user);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/"+user.getId())
                .buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(location).build();
    }


    @DeleteMapping("/users/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable Integer id){
        boolean isRemoved  = service.delete(id);
        if (!isRemoved ){
            throw new NotFoundException("User Not Found id " + id);
        }

        return new ResponseEntity<>(id, HttpStatus.NO_CONTENT);
    }

    @PostMapping("/users/{id}/posts")
    public ResponseEntity<Object> createPostUser(@PathVariable Integer id,@RequestBody Post post){
        boolean postCreated = service.createPost(id,post);
        if (!postCreated){
            throw new NotFoundException("User Not Found id " + id);
        }
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @GetMapping("/users/{id}/posts")
    public ResponseEntity<Object> allPostsUser(@PathVariable Integer id){
        ArrayList<Post> posts = service.getAllPosts(id);
        if (posts == null){
            throw new NotFoundException("User Not Found id " + id);
        }
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping("/users/{id}/posts/{postId}")
    public ResponseEntity<Object> allPostsUser(@PathVariable Integer id, @PathVariable Integer postId){
        Post post = service.getPostId(id,postId);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }


}
