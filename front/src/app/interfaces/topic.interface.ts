export interface Topic {
  id: number;
  title: string;
  description: string;
  isSubscribed: boolean;
}

export type SubscribedTopic = Pick<Topic, 'id' | 'title' | 'description'>;
