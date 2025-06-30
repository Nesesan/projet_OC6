package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.model.Topic;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ITopicService {
    List<Topic> getAllTopics();
    Optional<Topic> findTopicById(Long id);
}
