import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { SubscribedTopic, Topic } from '../interfaces/topic.interface';
import { BehaviorSubject, tap } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class TopicService {
  private apiPath: string = 'api/topic';

  public topics$: BehaviorSubject<Topic[] | null> = new BehaviorSubject<
    Topic[] | null
  >(null);

  constructor(private http: HttpClient) {}

  public getSubscribedTopics() {
    return this.http.get<SubscribedTopic[]>(`${this.apiPath}/subscribed`);
  }

  private fetchTopics() {
    return this.http.get<Topic[]>(`${this.apiPath}`);
  }

  public fetchSubscribedTopics() {
    return this.fetchTopics().pipe(
      tap((topics: Topic[]) => {
        this.topics$.next(topics.filter((t) => t.isSubscribed));
      })
    );
  }

  public fetchUnsubscribedTopics() {
    return this.fetchTopics().pipe(
      tap((topics: Topic[]) => {
        console.log(topics);
        this.topics$.next(topics.filter((t) => !t.isSubscribed));
      })
    );
  }

  public subscribe(id: number) {
    console.log('a');
    return this.http.post<Topic>(`${this.apiPath}/${id}/subscribe`, null).pipe(
      tap((topic: Topic) => {
        const topics = this.topics$.value;
        this.topics$.next(topics!.filter((t) => t.id !== topic.id));
      })
    );
  }

  public unsubscribe(id: number) {
    return this.http
      .post<Topic>(`${this.apiPath}/${id}/unsubscribe`, null)
      .pipe(
        tap((topic: Topic) => {
          const topics = this.topics$.value;
          this.topics$.next(topics!.filter((t) => t.id !== topic.id));
        })
      );
  }
}
