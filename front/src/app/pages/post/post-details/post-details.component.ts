import { Component, OnInit } from '@angular/core';
import {
  FormBuilder,
  FormControl,
  FormGroup,
  Validators,
} from '@angular/forms';
import { ActivatedRoute, ParamMap, Router } from '@angular/router';
import { Comment } from 'src/app/interfaces/comment.interface';
import { PostDetails } from 'src/app/interfaces/post-details.interface';
import { Post } from 'src/app/interfaces/post.interface';
import { PostService } from 'src/app/service/post.service';

@Component({
  selector: 'app-post-details',
  templateUrl: './post-details.component.html',
  styleUrl: './post-details.component.scss',
})
export class PostDetailsComponent implements OnInit {
  public post?: PostDetails;
  public commentForm: FormGroup = this.fb.group(
    {
      message: ['', [Validators.required, Validators.maxLength(255)]],
    },
    { updateOn: 'submit' }
  );

  get message() {
    return this.commentForm.get('message');
  }

  constructor(
    private route: ActivatedRoute,
    private postService: PostService,
    private router: Router,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.route.paramMap.subscribe((paramMap: ParamMap) => {
      this.postService.getPost(+paramMap.get('id')!).subscribe({
        next: (post) => (this.post = post),
        error: () => this.router.navigateByUrl('/not-found'),
      });
    });
  }

  public submit() {
    this.commentForm.markAsTouched();
    if (this.commentForm.valid) {
      this.postService
        .comment(this.post!.id, this.commentForm.getRawValue())
        .subscribe((comment: Comment) => {
          this.post!.comments.unshift(comment);
          this.commentForm.reset();
        });
    }
  }
}
