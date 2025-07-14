import {Topic} from "./topic";
import {PostComment} from "./postComment";

export interface Post {
  id: number;
  title: string;
  topic: Topic
  content: string;
  createdAt: Date;
  author: {
    id: number;
    username: string;
  };
  comments?: PostComment[];
}
