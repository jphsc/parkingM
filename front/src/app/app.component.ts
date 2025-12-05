import { AfterContentChecked, Component, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements AfterContentChecked {

  protected tituloPagina: string = "";
  protected mostrarCabecalho = true;

  constructor(private rota: Router) { }

  ngAfterContentChecked(): void {
    const url = this.rota.url;
    console.log(url)
    this.tituloPagina = this.getTituloPagina(url);
    this.mostrarCabecalho = !(url == '/pagina-nao-encontrada');
  }

  private getTituloPagina(url: string): string {

    let titulo:string = "";
    const regex = /^\/([^\/]+\/[^\/]+)/; //retorna todo o conteudo entre o primeiro e o terceiro '/'
    const match = url.match(regex)?.[0];

    if(url == '/home') {
      titulo = "Home";
      return titulo;
    }

    switch(match){
      case '/veiculo/cadastrar':
        titulo = "Cadastrar veículo";
        break;
      case '/veiculo/listar':
        titulo = "Listar veículos";
        break;
      case '/veiculo/editar':
        titulo = "Editar veículo";
        break;
      case '/veiculo/detalhe':
        titulo = "Detalhes do veículo";
        break;
      case '/movimento/cadastrar':
        titulo = "Gerar movimento de veículo";
        break;
      case '/movimento/listar':
        titulo = "Listar movimentos de veículos";
        break;
      case '/movimento/editar':
        titulo = "Encerrar movimento de veículo";
        break;
      case '/movimento/detalhe':
        titulo = "Detalhes do movimento do veículo";
        break;
      case '/regra/cadastrar':
        titulo = "Cadastrar regra financeira";
        break;
      case '/regra/listar':
        titulo = "Listar regras financeiras";
        break;
      case '/regra/editar':
        titulo = "Editar regra financeira";
        break;
      case '/regra/detalhe':
        titulo = "Detalhes da regra financeira";
        break;
    }
  return titulo;
  }
}
