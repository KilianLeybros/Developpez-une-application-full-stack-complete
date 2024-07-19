import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { MatIconModule } from '@angular/material/icon';
import { MatProgressBarModule } from '@angular/material/progress-bar';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './pages/home/home.component';
import { LoginComponent } from './pages/auth/login/login.component';
import { HeaderComponent } from './shared/header/header.component';
import { AuthTitleComponent } from './pages/auth/auth-title/auth-title.component';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { PostComponent } from './pages/post/post.component';
import { RegisterComponent } from './pages/auth/register/register.component';
import { PostListComponent } from './pages/post/post-list/post-list.component';
import { LoaderComponent } from './shared/loader/loader.component';
import { PostDetailsComponent } from './pages/post/post-details/post-details.component';
import { NotFoundComponent } from './shared/not-found/not-found.component';
import { PostFormComponent } from './pages/post/post-form/post-form.component';
import { TopicComponent } from './pages/topic/topic.component';
import { TopicListComponent } from './shared/topic-list/topic-list.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    LoginComponent,
    RegisterComponent,
    HeaderComponent,
    LoaderComponent,
    AuthTitleComponent,
    PostComponent,
    PostListComponent,
    PostDetailsComponent,
    NotFoundComponent,
    PostFormComponent,
    TopicComponent,
    TopicListComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    MatIconModule,
    MatProgressBarModule,
    ReactiveFormsModule,
    HttpClientModule,
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
