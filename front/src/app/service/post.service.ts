import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, delay, tap } from 'rxjs';
import { Post } from '../interfaces/post.interface';
import { Direction } from '../utils/direction.enum';

@Injectable({
  providedIn: 'root',
})
export class PostService {
  public posts$: BehaviorSubject<any | null> = new BehaviorSubject<any | null>(
    null
  );

  private apiPath: string = 'api/post';

  constructor(private http: HttpClient) {}

  public fetchPosts(direction: Direction, delayValue: number = 0) {
    const httpParams = new HttpParams({
      fromObject: {
        direction: direction,
      },
    });
    return this.http.get<Post[]>(this.apiPath, { params: httpParams }).pipe(
      delay(delayValue),
      tap((posts: Post[]) => {
        this.posts$.next(posts);
      })
    );
  }
}
