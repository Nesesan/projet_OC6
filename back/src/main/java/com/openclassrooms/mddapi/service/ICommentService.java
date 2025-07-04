package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.dto.CommentRequestDto;
import com.openclassrooms.mddapi.model.Comment;

public interface ICommentService {
    Comment addComment(Long postId, CommentRequestDto commentRequestDto , String username);
}
