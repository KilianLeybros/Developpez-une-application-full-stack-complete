import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import {
  BehaviorSubject,
  catchError,
  Observable,
  of,
  ReplaySubject,
  tap,
} from 'rxjs';
import { User } from '../interfaces/user.interface';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private path: String = 'api/auth';
  public isLoggedin$: ReplaySubject<boolean> = new ReplaySubject(1);
  public user$: BehaviorSubject<User | null> = new BehaviorSubject<User | null>(
    null
  );

  constructor(private http: HttpClient) {}

  public fetchCurrentUser(): Observable<User | null> {
    return this.http.get<User | null>(`${this.path}/authenticated`).pipe(
      tap((user: User | null) => {
        this.user$.next(user);
        if (user) {
          this.isLoggedin$.next(true);
        } else {
          this.isLoggedin$.next(false);
        }
      }),
      catchError((_) => {
        this.user$.next(null);
        this.isLoggedin$.next(false);
        return of(null);
      })
    );
  }

  public login(loginInput: {
    email: string;
    password: string;
  }): Observable<User> {
    return this.http.post<User>(`${this.path}/login`, loginInput).pipe(
      tap((user: User) => {
        if (user) {
          this.user$.next(user);
          this.isLoggedin$.next(true);
        }
      })
    );
  }

  public register(registerInput: {
    username: string;
    email: string;
    password: string;
  }): Observable<User> {
    return this.http.post<User>(`${this.path}/register`, registerInput).pipe(
      tap((user: User) => {
        if (user) {
          this.user$.next(user);
          this.isLoggedin$.next(true);
        }
      })
    );
  }

  public logout(): Observable<void> {
    return this.http.get<void>(`${this.path}/logout`).pipe(
      tap(() => {
        this.user$.next(null);
        this.isLoggedin$.next(false);
      })
    );
  }
}
