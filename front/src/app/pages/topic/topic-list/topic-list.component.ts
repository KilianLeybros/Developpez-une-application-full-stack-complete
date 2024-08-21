import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Topic } from 'src/app/interfaces/topic.interface';

@Component({
  selector: 'app-topic-list',
  templateUrl: './topic-list.component.html',
  styleUrl: './topic-list.component.scss',
})
export class TopicListComponent {
  @Input() public topics!: Topic[] | null;
  @Input() public isLoaded!: boolean;
  @Input() public emptyListMessage!: string;
  @Output() public action: EventEmitter<number> = new EventEmitter<number>();

  public updateSubscrition(id: number) {
    this.action.emit(id);
  }
}
