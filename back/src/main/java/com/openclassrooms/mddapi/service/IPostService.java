package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.dto.PostDto;
import com.openclassrooms.mddapi.dto.PostRequestDto;
import com.openclassrooms.mddapi.model.Post;

import java.util.List;

public interface IPostService {
    Post createPost(PostRequestDto postRequestDto, String email);
    List<PostDto> getAllPosts();
    PostDto getPostById(Long id);
}
