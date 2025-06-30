package com.openclassrooms.mddapi.service.impl;

import com.openclassrooms.mddapi.model.Topic;
import com.openclassrooms.mddapi.service.ITopicService;

import java.util.List;
import java.util.Optional;

public class TopicService  implements ITopicService {
    @Override
    public List<Topic> getAllTopics() {
        return List.of();
    }

    @Override
    public Optional<Topic> findTopicById(Long id) {
        return Optional.empty();
    }
}
