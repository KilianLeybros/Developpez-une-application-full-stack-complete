import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, tap } from 'rxjs';
import { User } from '../interfaces/user.interface';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private path: String = 'api/auth';

  public user$: BehaviorSubject<User | null> = new BehaviorSubject<User | null>(
    null
  );

  constructor(private http: HttpClient) {}

  public fetchCurrentUser(): Observable<User> {
    return this.http.get<User>(`${this.path}/authenticated`);
  }

  public login(loginInput: {
    email: string;
    password: string;
  }): Observable<User> {
    return this.http.post<User>(`${this.path}/login`, loginInput).pipe(
      tap((user: User) => {
        if (user) {
          this.user$.next(user);
        }
      })
    );
  }
}
