package com.web.travelagency.service.impl;

import com.web.travelagency.model.Location;
import com.web.travelagency.model.Posts;
import com.web.travelagency.model.User;
import com.web.travelagency.repository.LocationRepository;
import com.web.travelagency.repository.PostRepository;
import com.web.travelagency.repository.UserRepository;
import com.web.travelagency.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final LocationRepository locationRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository, UserRepository userRepository, LocationServiceImpl locationService, LocationRepository locationRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.locationRepository = locationRepository;
    }

    @Override
    public void createPost(Posts post) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (post.getPrivacy().equals("private")) {
            post.setPublic(false);
        }
        int locationId = Integer.parseInt(post.getLocation());
        Location location = locationRepository.findById(locationId);
        post.setLocation(location.getLocation());
        String username  = authentication.getName();
        User user = userRepository.findByUsername(username);
        post.setFirstName(user.getFirstName());
        post.setLastName(user.getLastName());
        post.setUser(user);
        postRepository.save(post);
    }

    @Override
    public List<Posts> getAllPosts() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username  = authentication.getName();
        User user = userRepository.findByUsername(username);
        List<Posts> getAllPost = postRepository.findByUserId(user.getId());
        return getAllPost;

//        List<Posts> getAllPosts = postRepository.findAll();
//        if (getAllPosts != null) {
//            return getAllPosts;
//        }
//        return null;
    }

    public List<Posts> getAllPublicPost(boolean isPublic) {
        try {
            List<Posts> getAllPublicPosts = postRepository.findByIsPublic(isPublic);
            return  getAllPublicPosts;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Optional<Posts> updatePost(int id) {
//        Optional<Posts> posts = postRepository.findById(id);
        return null;
    }

}
