import { Component, OnInit } from '@angular/core';
import { BehaviorSubject, Observable, of, ReplaySubject, Subject } from 'rxjs';
import { Topic } from 'src/app/interfaces/topic.interface';
import { TopicService } from 'src/app/service/topic.service';

@Component({
  selector: 'app-topic',
  templateUrl: './topic.component.html',
  styleUrl: './topic.component.scss',
})
export class TopicComponent implements OnInit {
  constructor(private topicService: TopicService) {}

  public emptyListMessage = 'Aucun théme pour le moment...';

  public isLoaded: boolean = false;

  public topics$: Observable<Topic[] | null> =
    this.topicService.topics$.asObservable();

  subscribe(id: number) {
    this.topicService.subscribe(id).subscribe();
  }

  ngOnInit(): void {
    this.topicService.getAll().subscribe(() => {
      this.isLoaded = true;
    });
  }
}
