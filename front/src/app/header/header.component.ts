import { Component, OnInit } from '@angular/core';
import {AuthService} from "../services/authService";
import {Router} from "@angular/router";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  constructor(
    private router: Router,
    private authService: AuthService
  ) { }

  ngOnInit(): void {
  }

  logOut(): void {
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
   this.router.navigate(['/users']);
  }

}
