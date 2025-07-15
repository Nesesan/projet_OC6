import { Component, OnInit } from '@angular/core';
import { Router } from "@angular/router";
import { AuthService } from "../services/authService";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {
  asideOpen = false;

  constructor(
    private router: Router,
    private authService: AuthService
  ) {}

  ngOnInit(): void {}

  toggleAside(): void {
    this.asideOpen = !this.asideOpen;
  }

  logOut(): void {
    this.toggleAside();
    this.authService.logOut();
    this.router.navigate(['/login']);
  }

  onPostsList(): void {
    this.router.navigate(['/posts']);
  }

  onTopicsList(): void {
    this.router.navigate(['/topics']);
  }

  onUserAccount(): void {
    this.router.navigate(['/user']);
  }
}
