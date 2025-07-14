package com.openclassrooms.mddapi.service.impl;

import com.openclassrooms.mddapi.dto.CommentRequestDto;
import com.openclassrooms.mddapi.model.Comment;
import com.openclassrooms.mddapi.model.Post;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.repository.CommentRepository;
import com.openclassrooms.mddapi.repository.PostRepository;
import com.openclassrooms.mddapi.repository.UserRepository;
import com.openclassrooms.mddapi.service.ICommentService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CommentService  implements ICommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public CommentService(CommentRepository commentRepository, UserRepository userRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    @Override
    public Comment addComment(Long postId, CommentRequestDto commentRequestDto, String username) {

        User user = userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        Post post = postRepository.findById(postId).orElseThrow(() -> new UsernameNotFoundException("Post not found"));

        Comment newComment = new Comment();
        newComment.setContent(commentRequestDto.getContent());
        newComment.setPost(post);
        newComment.setAuthor(user);
        newComment.setCreatedAt(LocalDateTime.now());

        return commentRepository.save(newComment);
    }
}
