import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { SubscribedTopic } from '../interfaces/topic.interface';

@Injectable({
  providedIn: 'root',
})
export class TopicService {
  private apiPath: string = 'api/topic';

  constructor(private httpt: HttpClient) {}

  public getSubscribedTopics() {
    return this.httpt.get<SubscribedTopic[]>(`${this.apiPath}/subscribed`);
  }
}
