import { NgModule } from '@angular/core';
import { PostDetailsComponent } from './post-details/post-details.component';
import { PostFormComponent } from './post-form/post-form.component';
import { PostListComponent } from './post-list/post-list.component';
import { PostComponent } from './post.component';
import { SharedModule } from 'src/app/shared/shared.module';
import { AppRoutingModule } from 'src/app/app-routing.module';
import { ReactiveFormsModule } from '@angular/forms';
import { MatIconModule } from '@angular/material/icon';

@NgModule({
  declarations: [
    PostDetailsComponent,
    PostFormComponent,
    PostListComponent,
    PostComponent,
  ],
  imports: [SharedModule, AppRoutingModule, ReactiveFormsModule, MatIconModule],
})
export class PostModule {}
