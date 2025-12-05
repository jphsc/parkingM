import { AfterViewChecked, Component, Output } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-card-lateral',
  templateUrl: './card-lateral.component.html',
  styleUrls: ['./card-lateral.component.css']
})
export class CardLateralComponent { //implements AfterViewChecked {

  // @Output() private nomeRota: string = "";  // Mude de string para Route


  // constructor(private rota: Router) {
  //   this.rota = rota;
  // }

  // ngAfterViewChecked(): void {
  //   this.nomeRota = this.getTituloPagina(this.rota.url);
  //   console.log(this.nomeRota);
  // }

  // private getTituloPagina(url: String): string {

  //   let titulo:string = "";

  //   switch(url){
  //     case '/home':
  //       titulo = "Home";
  //       break;
  //     case '/veiculo/cadastrar':
  //       titulo = "Cadastrar veículo";
  //       break;
  //     case '/veiculo/listar':
  //       titulo = "Listar veículos";
  //       break;
  //     case '/movimento/cadastrar':
  //       titulo = "Gerar movimento de veículo";
  //       break;
  //     case '/movimento/listar':
  //       titulo = "Listar movimentos de veículos";
  //       break;
  //     case '/regra/cadastrar':
  //       titulo = "Cadastrar regra financeira";
  //       break;
  //     case '/regra/listar':
  //       titulo = "Listar regras financeiras";
  //       break;
  //   }
  // return titulo;
  // }

}
