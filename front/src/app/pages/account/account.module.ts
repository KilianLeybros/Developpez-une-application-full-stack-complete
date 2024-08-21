import { NgModule } from '@angular/core';
import { AccountComponent } from './account.component';
import { AccountFormComponent } from './account-form/account-form.component';
import { TopicModule } from '../topic/topic.module';
import { SharedModule } from 'src/app/shared/shared.module';
import { ReactiveFormsModule } from '@angular/forms';

@NgModule({
  declarations: [AccountComponent, AccountFormComponent],
  imports: [SharedModule, TopicModule, ReactiveFormsModule],
})
export class AccountModule {}
