import { Component, OnInit } from '@angular/core';
import { AuthService } from './service/auth.service';
import { first, Observable } from 'rxjs';
import { User } from './interfaces/user.interface';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
})
export class AppComponent implements OnInit {
  private isLoggedin$: Observable<boolean | null> =
    this.authService.isLoggedin$.asObservable();

  public isLoggedin: boolean | null = null;

  constructor(private authService: AuthService) {}

  ngOnInit(): void {
    this.authService.isLoggedin$
      .pipe(first())
      .subscribe((value: boolean | null) => {
        this.isLoggedin = value;
      });
  }
}
