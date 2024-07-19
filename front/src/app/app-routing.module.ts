import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { LoginComponent } from './pages/auth/login/login.component';
import { dataUserGuard } from './guards/data-user.guard';
import { authGuard } from './guards/auth.guard';
import { PostComponent } from './pages/post/post.component';
import { loggedinGuard } from './guards/loggedin.guard';
import { RegisterComponent } from './pages/auth/register/register.component';
import { PostListComponent } from './pages/post/post-list/post-list.component';
import { PostDetailsComponent } from './pages/post/post-details/post-details.component';
import { NotFoundComponent } from './shared/not-found/not-found.component';
import { PostFormComponent } from './pages/post/post-form/post-form.component';

// consider a guard combined with canLoad / canActivate route option
// to manage unauthenticated user to access private routes
const routes: Routes = [
  {
    path: '',
    canActivate: [dataUserGuard, loggedinGuard],
    component: HomeComponent,
    pathMatch: 'full',
  },
  {
    path: 'login',
    canActivate: [dataUserGuard, loggedinGuard],
    component: LoginComponent,
  },
  {
    path: 'register',
    canActivate: [dataUserGuard, loggedinGuard],
    component: RegisterComponent,
  },
  {
    path: 'posts',
    canActivate: [dataUserGuard, authGuard],
    component: PostComponent,
  },
  {
    path: 'posts/new',
    pathMatch: 'full',
    canActivate: [dataUserGuard, authGuard],
    component: PostFormComponent,
  },
  {
    path: 'posts/:id',
    pathMatch: 'full',
    canActivate: [dataUserGuard, authGuard],
    component: PostDetailsComponent,
  },
  { path: 'not-found', component: NotFoundComponent },
  { path: '**', redirectTo: '/' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
