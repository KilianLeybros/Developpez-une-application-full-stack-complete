import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { MatIconModule } from '@angular/material/icon';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './pages/home/home.component';
import { HeaderComponent } from './header/header.component';
import { HttpClientModule } from '@angular/common/http';
import { NotFoundComponent } from './pages/not-found/not-found.component';
import { AccountModule } from './pages/account/account.module';
import { AuthModule } from './pages/auth/auth.module';
import { PostModule } from './pages/post/post.module';
import { TopicModule } from './pages/topic/topic.module';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    HeaderComponent,
    NotFoundComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    AccountModule,
    MatIconModule,
    AuthModule,
    PostModule,
    TopicModule,
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
