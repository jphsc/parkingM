import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { MovimentoVeiculo } from 'src/app/models/movimento-veiculo.model';
import { MovimentoVeiculoService } from 'src/app/services/movimento-veiculo.service';
import { Enumeradores } from 'src/app/utils/helper';

@Component({
  selector: 'app-movveiculo-listar',
  templateUrl: './movveiculo-listar.component.html',
  styleUrls: ['./movveiculo-listar.component.css']
})
export class MovveiculoListarComponent implements OnInit {

  movimentos:MovimentoVeiculo[] = [];

  constructor(private mvs: MovimentoVeiculoService){ }

  ngOnInit(): void {
    this.getAllMovimentos();
  }

  private getAllMovimentos():void {
    this.mvs.getAllMovimentos().subscribe(resp =>
      this.movimentos = resp.map(mv => {
        let tipoMovimento = Enumeradores.factory('TipoMovVeiculo').getDescricao(mv.tipoMovimento);
        let situacao = Enumeradores.factory('SituacaoMovimento').getDescricao(mv.situacao);

        mv.tipoMovimento = tipoMovimento;
        mv.situacao = situacao;

        return mv;
      })
    );
  }


}
