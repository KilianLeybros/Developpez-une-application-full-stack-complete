import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-auth-title',
  templateUrl: './auth-title.component.html',
  styleUrl: './auth-title.component.scss',
})
export class AuthTitleComponent {
  @Input() content!: string;
}
