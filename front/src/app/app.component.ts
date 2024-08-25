import { Component, OnDestroy, OnInit } from '@angular/core';
import { AuthService } from './service/auth.service';
import { filter, Observable, Subject, takeUntil } from 'rxjs';
import { ActivatedRoute, NavigationEnd, Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
})
export class AppComponent implements OnInit, OnDestroy {
  public isLoggedin$: Observable<boolean> =
    this.authService.isLoggedin$.asObservable();
  public isChildRoute?: boolean;

  public previousRoute?: string;

  private destroyed$ = new Subject();

  constructor(
    private authService: AuthService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.router.events
      .pipe(
        takeUntil(this.destroyed$),
        filter(
          (event): event is NavigationEnd => event instanceof NavigationEnd
        )
      )
      .subscribe((event: NavigationEnd) => {
        const splitedUrl = event.url.split('/');
        const routeLength = splitedUrl.length;
        this.previousRoute = splitedUrl[routeLength - 2];
        this.isChildRoute = routeLength > 2 ? true : false;
      });
  }

  ngOnDestroy(): void {
    this.destroyed$.next(true);
    this.destroyed$.complete();
  }
}
