import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Subject } from 'rxjs';
import { Post } from 'src/app/interfaces/post.interface';
import { SubscribedTopic } from 'src/app/interfaces/topic.interface';
import { PostService } from 'src/app/service/post.service';
import { TopicService } from 'src/app/service/topic.service';

@Component({
  selector: 'app-post-form',
  templateUrl: './post-form.component.html',
  styleUrl: './post-form.component.scss',
})
export class PostFormComponent implements OnInit {
  public topicList$?: Subject<SubscribedTopic[]> = new Subject<
    SubscribedTopic[]
  >();
  public error: string | null = null;
  public postForm: FormGroup = this.fb.group(
    {
      topicId: ['', [Validators.required, Validators.min(1)]],
      title: ['', [Validators.required, Validators.maxLength(100)]],
      description: ['', [Validators.required, Validators.maxLength(255)]],
    },
    { updateOn: 'submit' }
  );

  get topic() {
    return this.postForm.get('topicId');
  }

  get title() {
    return this.postForm.get('title');
  }

  get description() {
    return this.postForm.get('description');
  }

  constructor(
    private fb: FormBuilder,
    private topicService: TopicService,
    private postService: PostService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.topicService.fetchTopics().subscribe((topics: SubscribedTopic[]) => {
      this.topicList$?.next(topics);
    });
  }

  public submit() {
    this.postForm.markAsTouched();
    if (this.postForm.valid) {
      this.postService.addPost(this.postForm.getRawValue()).subscribe({
        next: (post: Post) => {
          this.router.navigateByUrl('posts');
        },
        error: (err) => {
          this.error = err.error;
        },
      });
    } else {
      this.error = null;
    }
  }
}
