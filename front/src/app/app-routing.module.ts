import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import {LoginComponent} from "./pages/login/login.component";
import {RegisterComponent} from "./pages/register/register.component";
import {PostComponent} from "./pages/post/post.component";
import {TopicsComponent} from "./pages/topics/topics.component";
import {PostsComponent} from "./pages/posts/posts.component";
import {NewPostComponent} from "./pages/new-post/new-post.component";
import {UserComponent} from "./pages/user/user.component";

// consider a guard combined with canLoad / canActivate route option
// to manage unauthenticated user to access private routes
const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'post/:id', component: PostComponent },
  { path: 'posts', component: PostsComponent },
  { path: 'topics', component: TopicsComponent },
  { path: 'new-post', component: NewPostComponent },
  { path: 'user', component: UserComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
