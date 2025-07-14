package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.dto.TopicDto;
import com.openclassrooms.mddapi.model.Topic;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.service.ITopicService;
import com.openclassrooms.mddapi.service.IUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/topics")
public class TopicController {

    private final ITopicService topicService;
    private final IUserService userService;

    public TopicController(ITopicService topicService, IUserService userService) {
        this.topicService = topicService;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<TopicDto>> getAllTopics() {
        List<TopicDto> topics = topicService.getAllTopics().stream()
                .map(topic -> new TopicDto(topic.getId(), topic.getName(), topic.getDescription()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(topics);
    }

    @PostMapping("/{topicId}/subscribe")
    public ResponseEntity<?> subscribeToTopic(@PathVariable Long topicId, Authentication authentication) {
        String email = authentication.getName();
        User user = userService.findByEmail(email).orElse(null);
        if (user == null) return ResponseEntity.status(404).body(Map.of("message", "User not found"));

        Topic topic = topicService.findTopicById(topicId).orElse(null);
        if (topic == null) return ResponseEntity.status(404).body(Map.of("message", "Topic not found"));

        if (user.getSubscribedTopics().contains(topic)) {
            return ResponseEntity.status(409).body(Map.of("message", "User is already subscribed to this topic"));
        }

        userService.subscribeToTopic(user, topic);
        return ResponseEntity.ok(Map.of("message", "Subscribed to topic: " + topic.getName()));
    }

    @PostMapping("/{topicId}/unsubscribe")
    public ResponseEntity<?> unsubscribeFromTopic(@PathVariable Long topicId, Authentication authentication) {
        String email = authentication.getName();
        User user = userService.findByEmail(email).orElse(null);
        if (user == null) return ResponseEntity.status(401).body(Map.of("message", "User not found"));

        Topic topic = topicService.findTopicById(topicId).orElse(null);
        if (topic == null) return ResponseEntity.status(404).body(Map.of("message", "Topic not found"));

        userService.unsubscribeFromTopic(user, topic);
        return ResponseEntity.ok(Map.of("message", "Unsubscribed from topic: " + topic.getName()));
    }

    @GetMapping("/subscribed")
    public ResponseEntity<List<Long>> getSubscribedTopicIds(Authentication authentication) {
        String email = authentication.getName();
        User user = userService.findByEmail(email).orElse(null);
        if (user == null) return ResponseEntity.status(401).build();

        List<Long> subscribedIds = user.getSubscribedTopics()
                .stream()
                .map(Topic::getId)
                .collect(Collectors.toList());

        return ResponseEntity.ok(subscribedIds);
    }
}
