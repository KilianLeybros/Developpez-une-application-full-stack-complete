import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { LoginComponent } from './pages/auth/login/login.component';
import { dataUserGuard } from './guards/data-user.guard';
import { authGuard } from './guards/auth.guard';
import { PostComponent } from './pages/post/post.component';
import { loggedinGuard } from './guards/loggedin.guard';
import { RegisterComponent } from './pages/auth/register/register.component';

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
    path: 'post',
    canActivate: [dataUserGuard, authGuard],
    component: PostComponent,
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
