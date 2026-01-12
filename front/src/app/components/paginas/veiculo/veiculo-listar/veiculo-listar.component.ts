import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { RespostaReqBackend } from 'src/app/models/resposta.model';
import { Veiculo } from 'src/app/models/veiculo.model';
import { VeiculoService } from 'src/app/services/veiculo.service';

@Component({
  selector: 'app-veiculo-listar',
  templateUrl: './veiculo-listar.component.html',
  styleUrls: ['./veiculo-listar.component.css']
})
export class VeiculoListarComponent implements OnInit {

  protected veiculos$:Veiculo[] = [];

  constructor(private veiculoService: VeiculoService){}

  ngOnInit(): void {
    this.carregarVeiculos();
  }

  private carregarVeiculos():void {
    this.veiculoService
      .getVeiculos()
      .subscribe(vs => this.veiculos$ = vs);
  }

  // protected veiculos$!:Observable<Veiculo[]>;

  // constructor(private veiculoService: VeiculoService){}

  // ngOnInit(): void {
  //   this.carregarVeiculos();
  // }

  // private carregarVeiculos():void {
  //   this.veiculos$ = this.veiculoService
  //     .getVeiculos()
  // }
}
