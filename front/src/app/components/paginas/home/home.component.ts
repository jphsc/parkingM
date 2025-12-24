import { Component, OnInit } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { Observable } from 'rxjs';
import { MovimentoVeiculo } from 'src/app/models/movimento-veiculo.model';
import { LoadingService } from 'src/app/services/loading.service';
import { MovimentoVeiculoService } from 'src/app/services/movimento-veiculo.service';
import { Enumeradores, Utils } from 'src/app/utils/helper';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  placa: string = '';
  movAbertos: MovimentoVeiculo[] = [];
  isLoading$: Observable<boolean>;
  loadingMessage$: Observable<string>;
  loaded: boolean = false;

  constructor(private mvs: MovimentoVeiculoService, private loadingService: LoadingService
    , private rota: RouterOutlet
  ) {
    this.isLoading$ = this.loadingService.isLoading$;
    this.loadingMessage$ = this.loadingService.loadingMessage$;
  }

  ngOnInit(): void {
    this.mvs.getMovimentosAbertos().subscribe(movvs => {

      movvs.forEach(mv => {
        const tipoMov:string = Enumeradores.factory('TipoMovVeiculo').getDescricao(mv.tipoMovimento);
        const situacaoMov:string = Enumeradores.factory('SituacaoMovimento').getDescricao(mv.situacao);

          mv.tipoMovimento = tipoMov;
          mv.situacao = situacaoMov;

          this.movAbertos.push(mv);
      })
      this.loaded = true;
      console.log(this.movAbertos)
    })
  }

  protected gerarMovimentoAvulso(): void {

    if (!this.placa || this.placa.trim() === '') {
      alert('Por favor, informe uma placa válida.');
      return;
    }

    console.log(this.placa);
  }

  public formatarPlaca():void {

    let placaFormatada = this.formatarPlacaInput(this.placa);
    this.placa = placaFormatada.length <= 7 ? placaFormatada : placaFormatada.substring(0,7);
    console.log(this.placa);
  }

  public formatarPlacaInput(placa: string): string {
    return placa.toUpperCase().replaceAll(/[^a-zA-Z0-9]/g, '');
  }
}
