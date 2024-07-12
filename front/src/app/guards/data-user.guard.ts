import { inject } from '@angular/core';
import { CanActivateFn } from '@angular/router';
import { AuthService } from '../service/auth.service';
import { catchError, first, map, of, switchMap, tap } from 'rxjs';
import { User } from '../interfaces/user.interface';

export const dataUserGuard: CanActivateFn = () => {
  const authService = inject(AuthService);
  return authService.user$.pipe(
    first(),
    switchMap((user: User | null) => {
      if (user) return of(true);
      else {
        return authService.fetchCurrentUser().pipe(
          tap((user: User) => {
            console.log(user);
            authService.user$.next(user);
          }),
          map(() => true)
        );
      }
    }),
    catchError((_) => {
      authService.user$.next(null);
      return of(true);
    })
  );
};
