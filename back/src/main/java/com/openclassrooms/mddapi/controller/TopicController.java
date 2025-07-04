package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.dto.TopicDto;
import com.openclassrooms.mddapi.service.impl.TopicService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/topics")
public class TopicController {

    private final TopicService topicService;

    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    @GetMapping
    public ResponseEntity<List<TopicDto>>getAllTopics() {
        List<TopicDto> topicDtos = topicService.getAllTopics().stream()
                .map(topic -> new TopicDto(topic.getId(), topic.getName(),topic.getDescription()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(topicDtos);
    }

}
