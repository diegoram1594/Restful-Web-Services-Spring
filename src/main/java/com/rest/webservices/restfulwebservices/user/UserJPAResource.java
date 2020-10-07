package com.rest.webservices.restfulwebservices.user;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class UserJPAResource {
    private final UserRepository userRepository;
    private final UserService userService;

    @Autowired
    public UserJPAResource(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @GetMapping("/jpa/users")
    public List<User> findAll(){
        return userRepository.findAll();
    }

    @GetMapping("/jpa/users/{id}")
    public EntityModel<MappingJacksonValue> findOne(@PathVariable Integer id){
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent()){
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

    @PostMapping("/jpa/users")
    public ResponseEntity<Object> saveUser(@Validated @RequestBody User user){
        user = userRepository.save(user);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/"+user.getId())
                .buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(location).build();
    }


    @DeleteMapping("/jpa/users/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable Integer id){
        userRepository.deleteById(id);
        //if (!isRemoved ){
        //    throw new NotFoundException("User Not Found id " + id);
       // }

        return new ResponseEntity<>(id, HttpStatus.NO_CONTENT);
    }

    @PostMapping("/jpa/users/{id}/posts")
    public ResponseEntity<Object> createPostUser(@PathVariable Integer id,@RequestBody Post post){

        Post postCreated = userService.createPost(id,post);
        if (postCreated == null){
            throw new NotFoundException("User Not Found id " + id);
        }
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }

    @GetMapping("/jpa/users/{id}/posts")
    public ResponseEntity<Object> allPostsUser(@PathVariable Integer id){
        List<Post> posts = userService.getAllPosts(id);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping("/jpa/users/{id}/posts/{postId}")
    public ResponseEntity<Object> allPostsUser(@PathVariable Integer id, @PathVariable Integer postId){
        Post post = userService.getPostId(id,postId);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }


}
