import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Topic} from "../../models/topic";
import {PostService} from "../../services/postService";
import {Router} from "@angular/router";
import {TopicService} from "../../services/topicService";

@Component({
  selector: 'app-new-post',
  templateUrl: './new-post.component.html',
  styleUrls: ['./new-post.component.scss']
})
export class NewPostComponent implements OnInit {
postForm: FormGroup = new FormGroup({});
topics: Topic[] = [];

  constructor(private formBuilder: FormBuilder, private router: Router, private topicService: TopicService, private postService: PostService) { }

  ngOnInit(): void {
    this.postForm = this.formBuilder.group({
      topicId: [null, [Validators.required]],
      title: ['', [Validators.required, Validators.minLength(3)]],
      content: ['', [Validators.required, Validators.minLength(3)]],
    });
    this.topicService.getAllTopics().subscribe((topics) => {
      this.topics = topics;
    });
  }

  onSubmit() {
    if(this.postForm.valid) {
      this.postService.createArticle(this.postForm.value).subscribe({
        next: (response) => {
          console.log("Article crée avec succès", response);
          this.router.navigate(['/posts']);
        },
        error: (error) => {
          console.error();
        }
      })
    }
  }
}
