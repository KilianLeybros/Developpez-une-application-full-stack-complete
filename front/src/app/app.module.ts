import { NgModule } from '@angular/core';
//import { MatLegacyButtonModule as MatButtonModule } from '@angular/material/legacy-button';
import { BrowserModule } from '@angular/platform-browser';
//import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatIconModule } from '@angular/material/icon';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './pages/home/home.component';
import { LoginComponent } from './pages/auth/login/login.component';
import { HeaderComponent } from './shared/header/header.component';
import { AuthTitleComponent } from './pages/auth/auth-title/auth-title.component';
import { ReactiveFormsModule } from '@angular/forms';
import { FakeHeaderComponent } from './shared/header/fake-header/fake-header.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    LoginComponent,
    HeaderComponent,
    FakeHeaderComponent,
    AuthTitleComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    MatIconModule,
    ReactiveFormsModule,
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
