import { Injectable } from '@angular/core';
import { AuthService } from './auth.service';
import { HttpClient } from '@angular/common/http';
import { Observable, tap } from 'rxjs';
import { User } from '../interfaces/user.interface';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  private path: string = '/api/user';
  constructor(private authService: AuthService, private http: HttpClient) {}

  public updateUserProfile(userInfo: {
    username: string;
    email: string;
  }): Observable<User> {
    return this.http.patch<User>(`${this.path}/info`, userInfo).pipe(
      tap((user: User) => {
        this.authService.user$.next(user);
      })
    );
  }
}
