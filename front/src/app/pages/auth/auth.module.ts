import { NgModule } from '@angular/core';
import { AuthTitleComponent } from './auth-title/auth-title.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { SharedModule } from 'src/app/shared/shared.module';
import { ReactiveFormsModule } from '@angular/forms';
import { MatIconModule } from '@angular/material/icon';
import { AppRoutingModule } from 'src/app/app-routing.module';

@NgModule({
  declarations: [AuthTitleComponent, LoginComponent, RegisterComponent],
  imports: [SharedModule, ReactiveFormsModule, MatIconModule, AppRoutingModule],
})
export class AuthModule {}
