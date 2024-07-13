import { Component, OnInit } from '@angular/core';
import { AuthService } from './service/auth.service';
import { first, Observable } from 'rxjs';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
})
export class AppComponent {
  public isLoggedin$: Observable<boolean> =
    this.authService.isLoggedin$.asObservable();

  constructor(private authService: AuthService) {}
}
