import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Topic } from 'src/app/interfaces/topic.interface';
import { User } from 'src/app/interfaces/user.interface';
import { AuthService } from 'src/app/service/auth.service';
import { TopicService } from 'src/app/service/topic.service';

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrl: './account.component.scss',
})
export class AccountComponent implements OnInit {
  constructor(
    private authService: AuthService,
    private topicService: TopicService
  ) {}

  public isLoaded: boolean = false;

  public topics$: Observable<Topic[] | null> =
    this.topicService.topics$.asObservable();

  public emptyListMessage: string = 'Vous êtes abonné à aucun théme';

  public user$: Observable<User | null> = this.authService.user$.asObservable();

  subscribe(id: number) {
    this.topicService.subscribe(id);
  }

  logout() {}

  public unsubscribe(id: number) {
    this.topicService.unsubscribe(id).subscribe();
  }

  ngOnInit(): void {
    this.topicService.fetchSubscribedTopics().subscribe(() => {
      this.isLoaded = true;
    });
  }
}
