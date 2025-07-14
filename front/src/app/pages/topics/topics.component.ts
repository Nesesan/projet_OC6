import {Component, OnInit} from '@angular/core';
import {Topic} from "../../models/topic";
import {TopicService} from "../../services/topicService";

@Component({
  selector: 'app-topics',
  templateUrl: './topics.component.html',
  styleUrls: ['./topics.component.scss']
})
export class TopicsComponent implements OnInit {
topics: Topic[] = [];
subscribedTopicsIds: number[] = [];

  constructor(private topicService: TopicService) { }

  ngOnInit(): void {
    this.topicService.getAllTopics().subscribe({
      next: data => this.topics = data,
      error: err => console.error()
    });

    this.loadSubscribedTopics();
  }

  loadSubscribedTopics(): void {
    this.topicService.getSubscribedTopicIds().subscribe({
      next: (ids: any[]) => {
        this.subscribedTopicsIds = ids.map(id => +id);
      },
      error: () => {
        this.subscribedTopicsIds = [];
      }
    });
  }
  isSubscribed(themeId: number): boolean {
    return this.subscribedTopicsIds.includes(themeId);
  }

  subscribeToTheme(topicId: number): void {
    this.topicService.subscribeToTopic(topicId).subscribe({
      next: () => {
        this.subscribedTopicsIds.push(topicId);
      },
      error: (error) => {
        if (error.status === 409) {
        }
      }
    });
  }


}
