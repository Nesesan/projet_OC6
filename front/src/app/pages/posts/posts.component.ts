import { Component, OnInit } from '@angular/core';
import { Post } from "../../models/post";
import { Router } from "@angular/router";
import { TopicService } from "../../services/topicService";
import {PostService} from "../../services/postService";

@Component({
  selector: 'app-posts',
  templateUrl: './posts.component.html',
  styleUrls: ['./posts.component.scss']
})
export class PostsComponent implements OnInit {
  posts: Post[] = [];
  isLoading = true;
  errorMessage = '';
  sortAsc: boolean = true;

  constructor(private postService: PostService, private router: Router, private topicService: TopicService) { }

  ngOnInit(): void {
    this.isLoading = true;

    this.topicService.getSubscribedTopicIds().subscribe({
      next: (subscribedIds: number[]) => {
        this.postService.getAllPosts().subscribe({
          next: (data) => {
            this.posts = data.filter(post => subscribedIds.includes(post.topic.id));
            this.sortPosts();
            this.isLoading = false;
          },
          error: () => {
            this.errorMessage = 'Erreur lors du chargement des articles.';
            this.isLoading = false;
            console.error();
          }
        });
      },
      error: () => {
        this.errorMessage = 'Erreur lors du chargement des abonnements.';
        this.isLoading = false;
        console.error();
      }
    });
  }

  sortPosts(): void {
    this.posts.sort((a, b) => {
      const dateA = new Date(a.createdAt).getTime();
      const dateB = new Date(b.createdAt).getTime();
      return this.sortAsc ? dateA - dateB : dateB - dateA;
    });
  }
  onCreateNewPost(): void {
    this.router.navigate(['/new-post']);
  }

  toggleSort() {
    this.sortAsc = !this.sortAsc;
    this.sortPosts();
  }
}
