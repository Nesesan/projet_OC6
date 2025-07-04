package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.dto.TopicDto;
import com.openclassrooms.mddapi.model.Topic;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.service.IUserService;
import com.openclassrooms.mddapi.service.impl.TopicService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/topics")
public class TopicController {

    private final TopicService topicService;
    private final IUserService userService;

    public TopicController(TopicService topicService, IUserService userService) {
        this.topicService = topicService;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<TopicDto>>getAllTopics() {
        List<TopicDto> topicDtos = topicService.getAllTopics().stream()
                .map(topic -> new TopicDto(topic.getId(), topic.getName(),topic.getDescription()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(topicDtos);
    }

    @PostMapping("/{topicId}/subscribe")
    public ResponseEntity<?> subscribeToTopic(@PathVariable Long topicId, Authentication authentication) {
        String email = authentication.getName();
        User user = userService.findByEmail(email).orElse(null);
        if (user == null) {
            return ResponseEntity.status(404).body(Map.of("message", "User not found"));
        }
        Topic topic = topicService.findTopicById(topicId).orElse(null);
        if (topic == null) {
            return ResponseEntity.status(404).body(Map.of("message", "Topic not found"));
        }
        if (user.getSubscribedTopics().contains(topic)) {
            return ResponseEntity.status(409).body(Map.of("message", "User has already subscribed topic"));
        }

        user.getSubscribedTopics().add(topic);
        return ResponseEntity.ok(Map.of("message", "Subscribed topic" + topic.getName()));
    }

    @PostMapping("/{topicId}/unsubscribe")
    public ResponseEntity<?> unsubscribeFromTopic(@PathVariable Long topicId, Authentication authentication) {
        String email = authentication.getName();
        User user = userService.findByEmail(email).orElse(null);
        if (user == null) {
            return ResponseEntity.status(404).body(Map.of("message", "User not found"));
        }
        Topic topic = topicService.findTopicById(topicId).orElse(null);
        if (topic == null) {
            return ResponseEntity.status(404).body(Map.of("message", "Topic not found"));
        }
        userService.unsubscribeFromTopic(user, topic);
        return ResponseEntity.ok(Map.of("message", "Unsubscribed topic" + topic.getName()));
    }

    @GetMapping("/subscribed")
    public ResponseEntity<List<Long>> getSubscribedTopics(Authentication authentication) {
        String email = authentication.getName();
        User user = userService.findByEmail(email).orElse(null);
        if (user == null) {
            return ResponseEntity.status(404).build();
        }

        List<Long> subscribedIds = user.getSubscribedTopics()
                .stream()
                .map(Topic::getId)
                .collect(Collectors.toList());

        return ResponseEntity.ok(subscribedIds);
    }

}
