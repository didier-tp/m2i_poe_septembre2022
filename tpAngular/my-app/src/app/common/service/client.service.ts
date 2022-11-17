import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Client } from '../data/client';

@Injectable({
  providedIn: 'root'
})
export class ClientService {

  baseUrl = "http://localhost:8080/appliSpringBoot/bank-api/client";

  constructor(private http :HttpClient) { }

  public postClient$(client :Client) : Observable<Client>{
    let url = this.baseUrl ; 
    return this.http.post<Client>(url,client);
  }
}
