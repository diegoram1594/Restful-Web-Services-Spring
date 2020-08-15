package com.rest.webservices.restfulwebservices.user;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;

@Component
public class UserDaoService {

    private static ArrayList<User> users = new ArrayList<>();
    private static Integer idCount = 3;

    static {
        users.add(new User(1,"Diego",new Date()));
        users.add(new User(2,"Lore",new Date()));
        users.add(new User(3,"Sergio",new Date()));
    }

    public ArrayList<User> findAll(){
        return users;
    }

    public User save(User user){
        user.setId(++idCount);
        users.add(user);
        return user;
    }

    public User findOne(Integer id){
        for (User user: users) {
            if (user.getId().equals(id)) return user;
        }
        return null;
    }

    public Boolean delete(Integer id){
        User user = findOne(id);
        if (user != null){
            users.remove(user);
            return true;
        }
        return false;
    }

    public Boolean createPost(Integer id,Post post){
        User user = findOne(id);
        if (user != null){
            post.setId(user.getPosts().size() + 1);
            post.setTimestamp(new Date());
            user.getPosts().add(post);
            return true;
        }
        return false;
    }

    public ArrayList<Post> getAllPosts(Integer id){
        User user = findOne(id);
        if (user != null){
            return user.getPosts();
        }
        return null;
    }

    public Post getPostId(Integer userId,Integer postId){
        User user = findOne(userId);
        if (user != null){
            for (Post post: user.getPosts()){
                if (post.getId().equals(postId)) {
                    return post;
                }
            }
            throw new NotFoundException("Post Not Found user id: " + userId + " post id: "+postId);
        }
        throw new NotFoundException("User Not Found id: "+userId);
    }


}
