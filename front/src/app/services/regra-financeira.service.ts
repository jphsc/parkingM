import { Injectable } from '@angular/core';
import { environment } from '../environment/environment.dev';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { RegraFinanceira } from '../models/regra-financeira';
import { RespostaReqBackend } from '../models/resposta.model';

@Injectable({
  providedIn: 'root'
})
export class RegraFinanceiraService {

  private baseUrlBackend = `${environment.baseUrlBackend}/regra-financeira`;

  constructor(private http: HttpClient) { }

  getAllRegras(): Observable<RespostaReqBackend<RegraFinanceira>> {
    return this.http
      .get<RespostaReqBackend<RegraFinanceira>>(`${this.baseUrlBackend}/regras`);
  }
}
