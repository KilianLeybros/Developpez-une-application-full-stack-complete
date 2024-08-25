import { Comment } from './comment.interface';

export interface PostDetails {
  id: number;
  title: string;
  date: Date;
  author: string;
  description: string;
  topic: string;
  comments: Comment[];
}
