import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { MovimentoVeiculo } from 'src/app/models/movimento-veiculo.model';
import { MovimentoVeiculoService } from 'src/app/services/movimento-veiculo.service';
import { Enumeradores } from 'src/app/utils/helper';

@Component({
  selector: 'app-movveiculo-detalhe',
  templateUrl: './movveiculo-detalhe.component.html',
  styleUrls: ['./movveiculo-detalhe.component.css']
})
export class MovveiculoDetalheComponent implements OnInit {

  movimento:MovimentoVeiculo = {};
  isTrueFalse:boolean = false;

  ngOnInit(): void {
    let idMovVeiculo = Number(this.rota.snapshot.paramMap.get("id"));
    let paginaAcao = this.rota.snapshot.url[0].path;
    this.isTrueFalse = (paginaAcao === 'detalhe') ? true : false;

    this.mvs.getMovimentoById(idMovVeiculo)
      .subscribe(resp => {

        let situacao = Enumeradores.factory('SituacaoMovimento').getDescricao(resp.situacao);
        let tipoMovimento = Enumeradores.factory('TipoMovVeiculo').getDescricao(resp.tipoMovimento);

        resp.situacao = situacao;
        resp.tipoMovimento = tipoMovimento;
        this.movimento = resp;
      });
  }

  constructor(private mvs:MovimentoVeiculoService, private rota: ActivatedRoute){}

}
