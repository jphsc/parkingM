import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { RegraFinanceira } from 'src/app/models/regra-financeira';
import { RespostaReqBackend } from 'src/app/models/resposta.model';
import { RegraFinanceiraService } from 'src/app/services/regra-financeira.service';
import { Enumeradores } from 'src/app/utils/helper';

@Component({
  selector: 'app-regra-listar',
  templateUrl: './regra-listar.component.html',
  styleUrls: ['./regra-listar.component.css']
})
export class RegraListarComponent implements OnInit {

  regras: RegraFinanceira[] = [];

  constructor(private regraFinService: RegraFinanceiraService){ }

  ngOnInit(): void {
    this.getAllRegras();
  }

  getAllRegras(): void {
    this.regraFinService.getAllRegras().subscribe({
      next: (resp: RespostaReqBackend<RegraFinanceira>) => {
        resp.registros.forEach(rf => {
          const tipoCobranca:string = Enumeradores.factory('TipoCobranca').getDescricao(rf.tipoCobranca);
          const tipoMovimento:string = Enumeradores.factory('TipoMovVeiculo').getDescricao(rf.tipoMovimento);
          const situacao:string = Enumeradores.factory('Situacao').getDescricao(rf.situacao);

          rf.tipoCobranca = tipoCobranca;
          rf.tipoMovimento = tipoMovimento;
          rf.situacao = situacao;

          this.regras.push(rf);
        })
      }
    });
  }
}
