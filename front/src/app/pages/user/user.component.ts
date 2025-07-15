import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import { Topic } from '../../models/topic';
import {UserService} from "../../services/userService";
import {TopicService} from "../../services/topicService";

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.scss']
})
export class UserComponent implements OnInit {
  userForm: FormGroup = new FormGroup({});
  themes: Topic[] = [];
  subscribedTopicsIds: number[] = [];
  subscribedThemes: Topic[] = [];

  constructor(
    private formBuilder: FormBuilder,
    private topicService: TopicService,
    private userService: UserService,
  ) {}

  ngOnInit(): void {
    this.userForm = this.formBuilder.group({
      username: ["", [Validators.required, Validators.minLength(5)]],
      password: ["", [Validators.required, Validators.minLength(8)]],
      email: ["", [Validators.required, Validators.email]],
    });

    this.loadSubscribedThemes();
  }

  loadSubscribedThemes(): void {
    this.topicService.getSubscribedTopicIds().subscribe({
      next: (ids: any[]) => {
        this.subscribedTopicsIds = ids.map(id => +id); // convertir string en number
        this.topicService.getAllTopics().subscribe({
          next: (allThemes: Topic[]) => {
            this.themes = allThemes;
            this.subscribedThemes = this.themes.filter(theme =>
              this.subscribedTopicsIds.includes(theme.id)
            );
          },
          error: err => console.error('Erreur chargement thèmes', err)
        });
      },
      error: err => {
        this.subscribedTopicsIds = [];
        this.subscribedThemes = [];
        console.error('Erreur chargement abonnements', err);
      }
    });
  }

  onSubmit(): void {
    if (this.userForm.invalid) return;

    const updateData = this.userForm.value;

    this.userService.updateCurrentUser(updateData).subscribe({
      next: () => {
        console.log("Successfully updated user", this.userForm.value);
        alert('Profil mis à jour avec succès');
      },
      error: (err: any) => {
        console.error('Erreur mise à jour utilisateur :', err);
        alert("Une erreur s'est produite");
      }
    });
  }

  unsubscribe(topicId: number): void {
    this.topicService.unsubscribeFromTopic(topicId).subscribe({
      next: () => {
        this.subscribedThemes = this.subscribedThemes.filter(theme => theme.id !== topicId);
        this.subscribedTopicsIds = this.subscribedTopicsIds.filter(id => id !== topicId);
        console.log(`Désabonné du thème ${topicId}`);
      },
      error: (err) => {
        console.error(`Erreur lors du désabonnement du thème ${topicId}`, err);
        alert("Erreur lors du désabonnement");
      }
    });
  }

}
