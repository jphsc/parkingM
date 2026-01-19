import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { RespostaReqBackend } from 'src/app/models/resposta.model';
import { Veiculo } from 'src/app/models/veiculo.model';
import { LoadingService } from 'src/app/services/loading.service';
import { VeiculoService } from 'src/app/services/veiculo.service';

@Component({
  selector: 'app-veiculo-listar',
  templateUrl: './veiculo-listar.component.html',
  styleUrls: ['./veiculo-listar.component.css']
})
export class VeiculoListarComponent implements OnInit {

  protected veiculos:Veiculo[] = [];
  protected isLoading$: Observable<boolean>;
  protected loadingMessage$: Observable<string>;
  protected isLoaded: boolean = false;

  constructor(private vs: VeiculoService, private rota:Router, private ls: LoadingService){
    this.isLoading$ = this.ls.isLoading$;
    this.loadingMessage$ = this.ls.loadingMessage$;
  }

  ngOnInit():void {
    this.getVeiculos();
  }

  navEditarVeiculo(id:any):void {
    this.rota.navigate([`/veiculo/editar/${id}`])
  }

  private getVeiculos():void {
    this.vs.getVeiculos()
      .subscribe({
        next: (resp) => {
          this.veiculos = resp.registros;
        },
        error: (err) => {
          console.error('Erro ao carregar veículos, código: ', err.error.codigo);
        },
        complete: () => {
          this.isLoaded = true;
        }
      });
  }
}
