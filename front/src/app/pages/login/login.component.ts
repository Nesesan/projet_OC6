import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AuthService} from "../../services/authService";


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup;

  constructor( private formBuilder: FormBuilder,private router: Router, private authService: AuthService ) {
    this.loginForm = formBuilder.group({
      identifier: ['', Validators.required],
      password: ['', [Validators.required, Validators.minLength(6)]],
    });
  }

  ngOnInit(): void {
  }

  back() {
    this.router.navigate(['/']);
  }

  onSubmit() {
    if (this.loginForm.invalid) return;

    const credentials = this.loginForm.value;

    this.authService.login(credentials).subscribe({
      next:(response)=> {
        this.router.navigate(['/posts']);
      },
      error:(error)=> {
        console.log(error);
        alert('Bad credentials');
      }
    })
  }
}
