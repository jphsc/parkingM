import { Component, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Observable } from 'rxjs';
import { MovimentoVeiculo } from 'src/app/models/movimento-veiculo.model';
import { LoadingService } from 'src/app/services/loading.service';
import { MovimentoVeiculoService } from 'src/app/services/movimento-veiculo.service';
import { Enumeradores } from 'src/app/utils/helper';
import { Utils } from 'src/app/utils/util';

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
  placaForm = new FormGroup({ placaInput: new FormControl('', [Validators.required, Validators.minLength(7)]) });

  constructor(private mvs: MovimentoVeiculoService, private loadingService: LoadingService) {
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
    });

    this.placaForm.get("placaInput")?.valueChanges.subscribe(value => {
      if(!value) return;

      this.formatarPlaca(value);

      // this.placaForm.get("placaInput")?.disable()
    })
  }

  protected gerarMovimentoAvulso(): void {

    console.log(this.placaForm.value);
  }

  public formatarPlaca(placa: string): void {
    const placaFormatada = Utils.formatarPlaca(placa);
    this.placaForm.get("placaInput")?.setValue(placaFormatada, { emitEvent: false });
  }
}
