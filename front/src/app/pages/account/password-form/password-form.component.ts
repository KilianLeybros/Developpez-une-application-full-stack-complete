import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/service/auth.service';
import { UserService } from 'src/app/service/user.service';

@Component({
  selector: 'app-password-form',
  templateUrl: './password-form.component.html',
  styleUrl: './password-form.component.scss',
})
export class PasswordFormComponent {
  public error?: string;
  public passwordForm: FormGroup = this.fb.group(
    {
      currentPassword: ['', Validators.required],
      newPassword: ['', Validators.required],
      confirm: ['', Validators.required],
    },
    { updateOn: 'submit' }
  );

  get currentPassword() {
    return this.passwordForm.get('currentPassword');
  }
  get newPassword() {
    return this.passwordForm.get('newPassword');
  }
  get confirm() {
    return this.passwordForm.get('confirm');
  }
  constructor(
    private fb: FormBuilder,
    private userService: UserService,
    private router: Router
  ) {}

  public submit() {
    this.error = undefined;
    this.passwordForm.markAsTouched();
    if (this.passwordForm.valid) {
      this.userService
        .updatePassword(this.passwordForm.getRawValue())
        .subscribe({
          next: () => this.router.navigateByUrl('/account'),
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
