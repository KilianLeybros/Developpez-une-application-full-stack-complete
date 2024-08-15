import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Topic } from 'src/app/interfaces/topic.interface';
import { User } from 'src/app/interfaces/user.interface';
import { AuthService } from 'src/app/service/auth.service';
import { UserService } from 'src/app/service/user.service';

@Component({
  selector: 'app-account-form',
  templateUrl: './account-form.component.html',
  styleUrl: './account-form.component.scss',
})
export class AccountFormComponent implements OnInit {
  public accountForm!: FormGroup;

  public error?: string;

  get username() {
    return this.accountForm.get('username');
  }

  get email() {
    return this.accountForm.get('email');
  }

  @Input() public user?: User | null;

  @Output() public logoutClicked: EventEmitter<void> = new EventEmitter<void>();

  ngOnInit(): void {
    this.buildForm();
  }

  constructor(private fb: FormBuilder, private userService: UserService) {}

  public buildForm() {
    if (this.user) {
      this.accountForm = this.fb.group(
        {
          username: [
            this.user.username,
            [Validators.required, Validators.maxLength(100)],
          ],
          email: [
            this.user.email,
            [Validators.required, Validators.email, Validators.maxLength(100)],
          ],
        },
        { updateOn: 'submit' }
      );
    }
  }

  public submit() {
    this.error = undefined;
    this.accountForm.markAsTouched();
    if (this.accountForm.valid) {
      this.userService
        .updateUserProfile(this.accountForm.getRawValue())
        .subscribe({
          error: (err) => {
            this.error =
              err.error && typeof err.error === 'string'
                ? err.error
                : "Mauvais email/nom d'utilisateur";
          },
        });
    }
  }

  public logout() {
    this.logoutClicked.emit(undefined);
  }
}
