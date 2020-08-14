package com.rest.webservices.restfulwebservices.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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
    public User findOne(@PathVariable Integer id){
        User user = service.findOne(id);
        if (user == null){
            throw new UserNotFoundException("id" +id );
        }
        return user;
    }

    @PostMapping("/users")
    public ResponseEntity<Object> saveUser(@RequestBody User user){
        user = service.save(user);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/id")
                .buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(location).build();
    }


}
