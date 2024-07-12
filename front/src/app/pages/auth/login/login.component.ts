import { log } from '@angular-devkit/build-angular/src/builders/ssr-dev-server';
import { Component, OnInit } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  Validators,
  FormsModule,
  FormControl,
} from '@angular/forms';
import { AuthService } from 'src/app/service/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss',
})
export class LoginComponent implements OnInit {
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
  constructor(private fb: FormBuilder, private authService: AuthService) {}

  ngOnInit(): void {
    console.log(this.loginForm);
  }
  public submit() {
    this.loginForm.markAsTouched();
    if (this.loginForm.valid) {
      this.authService.login(this.loginForm.getRawValue()).subscribe({
        next: () => console.log('logged'),
        error: (err) => {
          this.error = err.error || 'Mauvais email/mot de passe';
        },
      });
    }
  }
}
