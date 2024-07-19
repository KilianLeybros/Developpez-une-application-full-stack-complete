import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, ParamMap, Params, Router } from '@angular/router';
import { delay, Observable } from 'rxjs';
import { Post } from 'src/app/interfaces/post.interface';
import { PostService } from 'src/app/service/post.service';
import { Direction } from 'src/app/utils/direction.enum';

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrl: './post.component.scss',
})
export class PostComponent implements OnInit {
  public posts$: Observable<Post[]> = this.postService.posts$.asObservable();
  public direction: Direction = Direction.DESC;
  public isLoading: boolean = true;
  constructor(
    private postService: PostService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.route.queryParamMap.subscribe((paramMap: ParamMap) => {
      this.isLoading = true;
      this.direction =
        Direction[paramMap.get('direction') as keyof typeof Direction];

      if (!this.direction) {
        this.sort();
      } else {
        this.postService.fetchPosts(this.direction, 300).subscribe({
          next: () => {
            this.isLoading = false;
          },
          error: () => {
            this.isLoading = false;
          },
        });
      }
    });
  }

  sort() {
    this.isLoading = true;
    const direction =
      this.direction == Direction.DESC ? Direction.ASC : Direction.DESC;
    this.router.navigate([], {
      relativeTo: this.route,
      queryParams: {
        direction: direction,
      },
      queryParamsHandling: 'merge',
    });
  }
}
