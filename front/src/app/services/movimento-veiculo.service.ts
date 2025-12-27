import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../environment/environment.dev';
import { map, Observable } from 'rxjs';
import { MovimentoVeiculo } from '../models/movimento-veiculo.model';
import { RespostaReqBackend } from '../models/resposta.model';

@Injectable({
  providedIn: 'root'
})
export class MovimentoVeiculoService {

  private baseUrlBackend = `${environment.baseUrlBackend}/movimento-veiculo`;

  constructor(private http: HttpClient) { }

  getMovimentosAbertos():Observable<MovimentoVeiculo[]> {

    let filtro:MovimentoVeiculo = {
      situacao: 12 // 12 ABERTO | 13 ENCERRADO
    }

    return this.http
      .post<RespostaReqBackend<MovimentoVeiculo>>(`${this.baseUrlBackend}`, filtro)
      .pipe(
        map(resp => resp.registros.map((mvv: MovimentoVeiculo) => {
          const mv: MovimentoVeiculo = {
            id: mvv.id,
            idVeiculo: mvv.idVeiculo,
            idRegra: mvv.idRegra,
            placa: mvv.placa,
            tipoMovimento: mvv.tipoMovimento,
            dtHrEntrada: mvv.dtHrEntrada,
            dtHrSaida: mvv.dtHrSaida,
            situacao: mvv.situacao,
            versao: mvv.versao
        };

        return mv;
      })
    )
  )}
}
