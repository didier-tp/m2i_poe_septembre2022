import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Compte } from '../data/compte';
import { Virement } from '../data/virement';

@Injectable({
  providedIn: 'root'
})
export class CompteService {

  baseUrl = "http://localhost:8080/appliSpringBoot/bank-api/compte";

  constructor(private http :HttpClient) { }

  public rechercherComptesDuClient$(numClient:number) : Observable<Compte[]>{
    let url = this.baseUrl + "?numClient="+numClient; 
    return this.http.get<Compte[]>(url);
  }

  public postVirement$(demandeVirement :Virement) : Observable<Virement>{
    let url = this.baseUrl + "/virement"; 
    return this.http.post<Virement>(url,demandeVirement);
  }
}
