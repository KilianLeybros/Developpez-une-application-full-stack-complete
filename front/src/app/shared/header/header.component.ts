import {
  Component,
  HostListener,
  Input,
  OnDestroy,
  OnInit,
} from '@angular/core';
import {
  ActivatedRoute,
  NavigationEnd,
  Router,
  RouterStateSnapshot,
} from '@angular/router';
import { filter, map, Subscription, tap } from 'rxjs';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrl: './header.component.scss',
})
export class HeaderComponent implements OnInit, OnDestroy {
  @Input() public isLoggedin!: boolean | null;

  public ishomePage: boolean = true;

  public menuToggled: Boolean = false;

  private subscriptions = new Subscription();

  @HostListener('click', ['$event'])
  private onClick(event: Event) {
    const target = event.target as HTMLElement;
    if (target.getAttribute('aria-expanded')) {
      this.menuToggled = false;
    }
  }

  @HostListener('window:resize', ['$event'])
  private onResize(event: Event) {
    const target = event.target as Window;
    if (target.innerWidth > 768 && this.menuToggled) {
      this.menuToggled = false;
    } else {
      this.menuToggled = false;
    }
  }

  constructor(private router: Router, private route: ActivatedRoute) {}

  ngOnInit(): void {
    this.subscriptions = this.router.events
      .pipe(
        filter((event) => event instanceof NavigationEnd),
        map(() => this.router.routerState.snapshot),
        tap((route: RouterStateSnapshot) => {
          this.ishomePage = route.url == '/';
        })
      )
      .subscribe();
  }

  ngOnDestroy(): void {
    this.subscriptions.unsubscribe();
  }
}
