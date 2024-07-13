import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { AuthService } from '../service/auth.service';
import { map, of, tap } from 'rxjs';

export const loggedinGuard: CanActivateFn = (route, state) => {
  const authService = inject(AuthService);
  const router = inject(Router);
  return authService.isLoggedin$.pipe(
    tap((isLoggedin: boolean) => {
      if (!isLoggedin) {
      } else {
        router.navigateByUrl('/post');
      }
    }),
    map(() => true)
  );
};
