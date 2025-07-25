package com.openclassrooms.mddapi.dto;


import com.openclassrooms.mddapi.model.Comment;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CommentDto {
    public Long id;
    public String content;
    public LocalDateTime createdAt;
    public String authorUsername;

    public CommentDto(Comment comment) {
        this.id = comment.getId();
        this.content = comment.getContent();
        this.authorUsername = comment.getAuthor().getUsername();
        this.createdAt = comment.getCreatedAt();
    }

}
