import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/service/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss',
})
export class LoginComponent {
  public error?: string;
  public loginForm: FormGroup = this.fb.group(
    {
      email: ['', Validators.required],
      password: ['', Validators.required],
    },
    { updateOn: 'submit' }
  );

  get email() {
    return this.loginForm.get('email');
  }
  get password() {
    return this.loginForm.get('password');
  }
  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private router: Router
  ) {}

  public submit() {
    this.error = undefined;
    this.loginForm.markAsTouched();
    if (this.loginForm.valid) {
      this.authService.login(this.loginForm.getRawValue()).subscribe({
        next: () => this.router.navigateByUrl('/posts'),
        error: (err) => {
          this.error =
            err.error && typeof err.error === 'string'
              ? err.error
              : 'Mauvais email/mot de passe';
        },
      });
    }
  }
}
