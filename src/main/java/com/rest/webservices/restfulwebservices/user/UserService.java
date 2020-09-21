package com.rest.webservices.restfulwebservices.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public Post createPost( Integer userId,  Post post){
        Optional<User> user = userRepository.findById(userId);
        if (!user.isPresent()){
            return null;
        }
        post.setTimestamp(new Date());
        user.get().getPosts().add(post);
        userRepository.save(user.get());
        return post;
    }

    public List<Post> getAllPosts(Integer userId) {
        Optional<User> user = userRepository.findById(userId);
        if (!user.isPresent()){
            throw new NotFoundException("User Not Found id " + userId);
        }
        return user.get().getPosts();
    }

    public Post getPostId(Integer userId, Integer postId) {
        Optional<User> user = userRepository.findById(userId);
        if (!user.isPresent()){
            throw new NotFoundException("User Not Found id " + userId);
        }
        Optional<Post> res = user.get().getPosts().stream().filter(post -> post.getId() == postId).findFirst();
        if (!res.isPresent()){
            throw new NotFoundException("Post Not Found id " + postId);
        }
        return res.get();
    }
}
