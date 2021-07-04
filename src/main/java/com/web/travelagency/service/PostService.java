package com.web.travelagency.service;

import com.web.travelagency.model.Posts;

import java.util.List;
import java.util.Optional;

public interface PostService {
    void createPost(Posts post);
    List<Posts> getAllPosts();
    List<Posts> getAllPublicPost(boolean isPublic);
    Optional<Posts> updatePost(int id);
}
