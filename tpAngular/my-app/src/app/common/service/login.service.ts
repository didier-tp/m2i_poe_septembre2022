import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { LoginRequest } from '../data/login_request';
import { LoginResponse } from '../data/login_response';


@Injectable({
  providedIn: 'root'
})
export class LoginService {

  baseUrl = "http://localhost:8080/appliSpringBoot/api-login/public/login";

  constructor(private http :HttpClient) { }

  public postLogin$(loginRequest :LoginRequest) : Observable<LoginResponse>{
    let url = this.baseUrl ; 
    return this.http.post<LoginResponse>(url,loginRequest);
  }
}
