import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { AuthService } from '../service/auth.service';
import { first, of, tap } from 'rxjs';

export const authGuard: CanActivateFn = (route, state) => {
  console.log(route);
  const authService = inject(AuthService);
  const router = inject(Router);
  return authService.isLoggedin$.pipe(
    first(),
    tap((isLoggedin: boolean) => {
      if (!isLoggedin) {
        router.navigateByUrl('/login');
      }
    })
  );
};
