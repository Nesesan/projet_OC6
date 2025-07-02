package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.model.Comment;

public interface ICommentService {
    Comment addComment(Long postId, Comment comment , String username);
}
