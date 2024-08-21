import { NgModule } from '@angular/core';
import { TopicComponent } from './topic.component';
import { TopicListComponent } from 'src/app/pages/topic/topic-list/topic-list.component';
import { SharedModule } from 'src/app/shared/shared.module';

@NgModule({
  declarations: [TopicComponent, TopicListComponent],
  imports: [SharedModule],
  exports: [TopicListComponent],
})
export class TopicModule {}
