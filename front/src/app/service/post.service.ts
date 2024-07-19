import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, delay, tap } from 'rxjs';
import { Post } from '../interfaces/post.interface';
import { Direction } from '../utils/direction.enum';
import { PostDetails } from '../interfaces/post-details.interface';
import { Comment } from '../interfaces/comment.interface';

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

  public getPost(id: number) {
    return this.http.get<PostDetails>(`${this.apiPath}/${id}`);
  }

  public comment(id: number, message: string) {
    return this.http.post<Comment>(`${this.apiPath}/${id}/comment`, message);
  }
}
