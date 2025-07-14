import { Component, OnInit } from '@angular/core';
import {Post} from "../../models/post";
import {ActivatedRoute, Router} from "@angular/router";
import {AuthService} from "../../services/authService";
import {PostComment} from "../../models/postComment";
import {PostService} from "../../services/post.service";

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.scss']
})
export class PostComponent implements OnInit {
  post!: Post;
  isLoading = true;
  errorMessage = '';
  newComment: string = '';

  constructor( private route: ActivatedRoute,
               private postService: PostService,
               private router: Router,
               private authService: AuthService,) { }

  ngOnInit(): void {
    const id = +this.route.snapshot.paramMap.get('id')!;
    this.postService.getPostById(id).subscribe({
      next: (data: Post) => {
        this.post = data;
        this.isLoading = false;
      },
      error: () => {
        this.errorMessage = 'Article introuvable.';
        this.isLoading = false;
      }
    });
  }

  back(): void {
    this.router.navigate(['/posts']);
  }

  submitComment() {
    const content = this.newComment.trim();
    if (!content) return;

    const currentUserId = this.authService.getCurrentUserId();
    if (!currentUserId) {
      this.errorMessage = "Vous devez être connecté pour commenter.";
      return;
    }

    this.postService.addComment(this.post.id, {
      authorId: currentUserId,
      content: content
    }).subscribe({
      next: (comment: PostComment) => {
        this.post.comments = this.post.comments || [];
        this.post.comments.push(comment);
        this.newComment = '';
        this.errorMessage = '';
      },
      error: err => {
        this.errorMessage = 'Erreur lors de l\'ajout du commentaire.';
        console.error(err);
      }
    });
  }

}
