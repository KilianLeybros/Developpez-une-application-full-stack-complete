import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, tap } from 'rxjs';
import { User } from '../interfaces/user.interface';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private path: String = 'api/auth';

  public $user: BehaviorSubject<User | null> = new BehaviorSubject<User | null>(
    null
  );

  constructor(private http: HttpClient) {}

  login(loginInput: { email: string; password: string }): Observable<User> {
    return this.http.post<any>(`${this.path}/login`, loginInput).pipe(
      tap((user: User) => {
        if (user) {
          this.$user.next(user);
        }
      })
    );
  }
}
