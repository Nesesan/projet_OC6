package com.openclassrooms.mddapi.dto;

import com.openclassrooms.mddapi.model.Topic;
import lombok.Getter;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.openclassrooms.mddapi.model.Post;

import lombok.Setter;

@Getter
@Setter
public class PostDto {
    private Long id;
    private String title;
    private String content;
    private Topic topic;
    private LocalDateTime createdAt;
    private AuthorDto author;
    private List<CommentDto> comments;

    public PostDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.topic = post.getTopic();
        this.content = post.getContent();
        this.createdAt = post.getCreatedAt();
        this.author = new AuthorDto(post.getAuthor());
        this.comments = post.getComments().stream()
                .map(CommentDto::new)
                .collect(Collectors.toList());
    }
}
