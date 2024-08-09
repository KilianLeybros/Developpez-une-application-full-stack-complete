import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Topic } from 'src/app/interfaces/topic.interface';
import { User } from 'src/app/interfaces/user.interface';
import { AuthService } from 'src/app/service/auth.service';

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


  ngOnInit(): void {
    this.buildForm();
  }

  constructor(private fb: FormBuilder) {}

  public buildForm() {
    if (this.user) {
      this.accountForm = this.fb.group(
        {
          username: [this.user.username, [Validators.required]],
          email: [this.user.email, [Validators.required]],
        },
        { updateOn: 'submit' }
      );
    }
  }

  public submit() {}

  public logout() {}
}
