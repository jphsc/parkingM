import { Injectable } from '@angular/core';
import { environment } from '../environment/environment.dev';
import { HttpClient } from '@angular/common/http';
import { map, Observable } from 'rxjs';
import { RegraFinanceira } from '../models/regra-financeira';
import { RespostaReqBackend } from '../models/resposta.model';

@Injectable({
  providedIn: 'root'
})
export class RegraFinanceiraService {

  private baseUrlBackend = `${environment.baseUrlBackend}/regra-financeira`;

  constructor(private http: HttpClient) { }

  getAllRegras(): Observable<RegraFinanceira[]> {
    return this.http
    .get<RespostaReqBackend<RegraFinanceira>>(`${this.baseUrlBackend}/regras`)
    .pipe(map(resp => resp.registros.map(rf => this.mapRegraFinanceira(rf))));
  }

  private mapRegraFinanceira(rfr: RegraFinanceira):RegraFinanceira {
    const rf: RegraFinanceira = {
      id: rfr.id,
      descricao: rfr.descricao,
      dtHrInicioValidade: rfr.dtHrInicioValidade,
      dtHrFimValidade: rfr.dtHrFimValidade,
      situacao: rfr.situacao,
      tipoCobranca: rfr.tipoCobranca,
      tipoMovimento: rfr.tipoMovimento,
      valor: rfr.valor,
      versao: rfr.versao
    };

    return rf;
  }
}
