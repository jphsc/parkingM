import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../environment/environment.dev';
import { Veiculo } from '../models/veiculo.model';
import { map, Observable } from 'rxjs';
import { RespostaReqBackend } from '../models/resposta.model';

@Injectable({
  providedIn: 'root'
})
export class VeiculoService {

  private baseUrlBackend = `${environment.baseUrlBackend}/veiculo`;

  constructor(private http: HttpClient) { }

  getVeiculos():Observable<Veiculo[]> {
    return this.http
      .get<RespostaReqBackend<Veiculo>>(`${this.baseUrlBackend}/veiculos`)
      .pipe(
        map(resp => resp.registros.map(veiculo => {
          const v: Veiculo = {
            id: veiculo.id,
            modelo: veiculo.modelo,
            montadora: veiculo.montadora,
            dtRegistro: veiculo.dtRegistro,
            placa: veiculo.placa,
            versao: veiculo.versao
          };
        return v;
      })
    )
  )}
}
