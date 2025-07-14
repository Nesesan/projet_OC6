import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable, tap} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrl = 'http://localhost:8080/api/auth';

  constructor(private http: HttpClient) { }

  register(user: any): Observable<any> {
    return this.http.post(`${this.apiUrl}/register`, user);
  }

  login(credentials: {identifier: string, password: string}): Observable<any> {
    return this.http.post<{token: string}>(`${this.apiUrl}/login`, credentials)
      .pipe(
        tap(response => {
          localStorage.setItem('jwtToken', response.token);
        })
      );
  }

  getCurrentUserId(): number | null {
    const token = localStorage.getItem('jwtToken');
    if (!token) return null;

    let payloadBase64 = token.split('.')[1];
    if (!payloadBase64) return null;

    payloadBase64 = payloadBase64.replace(/-/g, '+').replace(/_/g, '/');

    try {
      const payloadJson = atob(payloadBase64);
      const payload = JSON.parse(payloadJson);
      return payload.id || null;
    } catch (e) {
      console.error();
      return null;
    }
  }


  logOut() {
    localStorage.removeItem('jwtToken');
  }
}
