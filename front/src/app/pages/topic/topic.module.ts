import { NgModule } from '@angular/core';
import { TopicComponent } from './topic.component';
import { TopicListComponent } from 'src/app/pages/topic/topic-list/topic-list.component';
import { SharedModule } from 'src/app/shared/shared.module';
import { MatIconModule } from '@angular/material/icon';

@NgModule({
  declarations: [TopicComponent, TopicListComponent],
  imports: [SharedModule, MatIconModule],
  exports: [TopicListComponent],
})
export class TopicModule {}
