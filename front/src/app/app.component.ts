import { Component } from '@angular/core';
import { AuthService } from './service/auth.service';
import { Observable } from 'rxjs';
import { User } from './interfaces/user.interface';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
})
export class AppComponent {
  public user$: Observable<User | null> = this.authService.user$.asObservable();

  constructor(private authService: AuthService) {}
}
