import { log } from '@angular-devkit/build-angular/src/builders/ssr-dev-server';
import { Component, OnInit } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  Validators,
  FormsModule,
  FormControl,
} from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss',
})
export class LoginComponent implements OnInit {
  public loginForm: FormGroup = this.fb.group(
    {
      username: ['', Validators.required],
      password: ['', Validators.required],
    },
    { updateOn: 'submit' }
  );

  get username() {
    return this.loginForm.get('username');
  }
  get password() {
    return this.loginForm.get('password');
  }
  constructor(private fb: FormBuilder) {}

  ngOnInit(): void {
    console.log(this.loginForm);
  }
  public submit() {
    this.loginForm.markAsTouched();
  }
}
