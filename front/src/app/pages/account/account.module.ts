import { NgModule } from '@angular/core';
import { AccountComponent } from './account.component';
import { AccountFormComponent } from './account-form/account-form.component';
import { TopicModule } from '../topic/topic.module';
import { SharedModule } from 'src/app/shared/shared.module';
import { ReactiveFormsModule } from '@angular/forms';
import { PasswordFormComponent } from './password-form/password-form.component';
import { MatIconModule } from '@angular/material/icon';
import { AppRoutingModule } from 'src/app/app-routing.module';

@NgModule({
  declarations: [AccountComponent, AccountFormComponent, PasswordFormComponent],
  imports: [
    SharedModule,
    TopicModule,
    ReactiveFormsModule,
    MatIconModule,
    AppRoutingModule,
  ],
})
export class AccountModule {}
