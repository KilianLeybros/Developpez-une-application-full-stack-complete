import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-post-form',
  templateUrl: './post-form.component.html',
  styleUrl: './post-form.component.scss',
})
export class PostFormComponent {
  public postFrom: FormGroup = this.fb.group(
    {
      topic: ['', [Validators.required]],
      title: ['', [Validators.required]],
      description: ['', [Validators.required]],
    },
    { updateOn: 'submit' }
  );

  constructor(private fb: FormBuilder) {}

  public submit() {}
}
