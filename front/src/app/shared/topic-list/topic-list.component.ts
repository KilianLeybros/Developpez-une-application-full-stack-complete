import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Topic } from 'src/app/interfaces/topic.interface';

@Component({
  selector: 'app-topic-list',
  templateUrl: './topic-list.component.html',
  styleUrl: './topic-list.component.scss',
})
export class TopicListComponent {
  @Input() public topics!: Topic[] | null;
  @Input() public isLoading!: boolean;
  @Output() public action: EventEmitter<number> = new EventEmitter<number>();

  public updateSubscrition(id: number) {
    console.log(id);
    this.action.emit(id);
  }
}
