package com.openclassrooms.mddapi.service.impl;

import com.openclassrooms.mddapi.model.Post;
import com.openclassrooms.mddapi.service.IPostService;

import java.util.List;

public class PostService  implements IPostService {
    @Override
    public Post createPost(Post post) {
        return null;
    }

    @Override
    public List<Post> getAllPosts() {
        return List.of();
    }

    @Override
    public Post getPostById(Long id) {
        return null;
    }
}
