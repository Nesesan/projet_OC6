package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.model.Post;

import java.util.List;

public interface IPostService {
    Post createPost(Post post);
    List<Post> getAllPosts();
    Post getPostById(Long id);
}
