import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { MovimentoVeiculo } from 'src/app/models/movimento-veiculo.model';
import { RespostaReqBackend } from 'src/app/models/resposta.model';
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
      .subscribe({
        next: (resp: RespostaReqBackend<MovimentoVeiculo>) => {
          resp.registros.forEach((mv:MovimentoVeiculo) => {
            let situacao = Enumeradores.factory('SituacaoMovimento').getDescricao(mv.situacao);
            let tipoMovimento = Enumeradores.factory('TipoMovVeiculo').getDescricao(mv.tipoMovimento);

            mv.situacao = situacao;
            mv.tipoMovimento = tipoMovimento;
            this.movimento = mv;
          })
        }
      }
    );
  }

  constructor(private mvs:MovimentoVeiculoService, private rota: ActivatedRoute){}

}
