import {Observable} from "rxjs";
import {Topic} from "../models/topic";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Injectable} from "@angular/core";

@Injectable({
  providedIn: 'root'
})
export class TopicService {
  private apiUrl = 'http://localhost:8080/api/topics';

  constructor(private http: HttpClient) {}

  getAllTopics(): Observable<Topic[]> {
    return this.http.get<Topic[]>(this.apiUrl);
  }

  subscribeToTopic(topicId: number): Observable<string> {
    const token = localStorage.getItem('jwtToken');
    const headers = token ? new HttpHeaders().set('Authorization', `Bearer ${token}`) : undefined;

    return this.http.post(
      `${this.apiUrl}/${topicId}/subscribe`,
      null,
      { headers, responseType: 'text' }
    );
  }

  getSubscribedTopicIds(): Observable<number[]> {
    const token = localStorage.getItem('jwtToken');
    const headers = token ? new HttpHeaders().set('Authorization', `Bearer ${token}`) : undefined;

    return this.http.get<number[]>(`${this.apiUrl}/subscribed`, { headers });
  }


  unsubscribeFromTopic(topicId: number): Observable<any> {
    const token = localStorage.getItem('jwtToken');
    const headers = token ? new HttpHeaders().set('Authorization', `Bearer ${token}`) : undefined;

    return this.http.post(`${this.apiUrl}/${topicId}/unsubscribe`, {}, { headers });
  }

}

