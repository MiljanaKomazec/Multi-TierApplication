import { Dijagnoza } from './../models/dijagnoza';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { DIJAGNOZA_URL } from '../constants';

@Injectable({
  providedIn: 'root'
})
export class DijagnozaService {

  constructor(private httpClient: HttpClient) { }

  public getAllDijagnoza(): Observable<any> {
    return this.httpClient.get(`${DIJAGNOZA_URL}`);
  }

  public addDijagnoza(dijagnoza:Dijagnoza):Observable<any>{
    return this.httpClient.post(`${DIJAGNOZA_URL}` ,dijagnoza);
  }

  public updateDijagnoza(dijagnoza:Dijagnoza):Observable<any>{
    return this.httpClient.put(`${DIJAGNOZA_URL}/${dijagnoza.id}` ,dijagnoza);
  }

  public deleteDijagnoza(id:number):Observable<any> {
    return this.httpClient.delete(`${DIJAGNOZA_URL}/${id}`);
  }

}
