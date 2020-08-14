package com.rest.webservices.restfulwebservices.user;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;

@Component
public class UserDaoService {

    private static ArrayList<User> users = new ArrayList<>();

    static {
        users.add(new User(1,"Diego",new Date()));
        users.add(new User(2,"Lore",new Date()));
        users.add(new User(3,"Sergio",new Date()));
    }

    public ArrayList<User> findAll(){
        return users;
    }

    public User save(User user){
        user.setId(users.size()+1);
        users.add(user);
        return user;
    }

    public User findOne(Integer id){
        for (User user: users) {
            if (user.getId().equals(id)) return user;
        }
        return null;
    }
}
