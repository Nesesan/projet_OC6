package com.openclassrooms.mddapi.dto;

import com.openclassrooms.mddapi.model.Topic;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TopicDto {
    private Long id;
    private String name;
    private String description;


    public TopicDto(Topic topic) {
        this.id = topic.getId();
        this.name = topic.getName();
        this.description = topic.getDescription();
    }
}
