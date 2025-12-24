import { AfterContentChecked, AfterContentInit, Component, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements AfterContentChecked, AfterContentInit {

  protected tituloPagina: string = "";
  protected mostrarCabecalho = true;
  public title = 'front';

  constructor(private rota: Router, private titleService: Title) { }

  ngAfterContentInit(): void {
    console.log(this.rota)
  }


  ngAfterContentChecked(): void {
    const url = this.rota.url;
    this.tituloPagina = this.getTituloPagina(url);
    this.mostrarCabecalho = !(url == '/pagina-nao-encontrada');
    this.titleService.setTitle(this.mostrarCabecalho ? `ParkingM - ${this.tituloPagina}` : 'ParkingM - Página não encontrada');
  }

  private getTituloPagina(url: string): string {

    const regex = /^\/([^\/]+\/[^\/]+)/; //retorna todo o conteudo entre o primeiro e o terceiro '/'
    const match = url.match(regex)?.[0];

    if(url == '/home') {
      this.title = "Home";
      return this.title;
    }

    switch(match){
      case '/veiculo/cadastrar':
        this.title = "Cadastrar veículo";
        break;
      case '/veiculo/listar':
        this.title = "Listar veículos";
        break;
      case '/veiculo/editar':
        this.title = "Editar veículo";
        break;
      case '/veiculo/detalhe':
        this.title = "Detalhes do veículo";
        break;
      case '/movimento/cadastrar':
        this.title = "Gerar movimento de veículo";
        break;
      case '/movimento/listar':
        this.title = "Listar movimentos de veículos";
        break;
      case '/movimento/editar':
        this.title = "Encerrar movimento de veículo";
        break;
      case '/movimento/detalhe':
        this.title = "Detalhes do movimento do veículo";
        break;
      case '/regra/cadastrar':
        this.title = "Cadastrar regra financeira";
        break;
      case '/regra/listar':
        this.title = "Listar regras financeiras";
        break;
      case '/regra/editar':
        this.title = "Editar regra financeira";
        break;
      case '/regra/detalhe':
        this.title = "Detalhes da regra financeira";
        break;
    }
  return this.title;
  }
}
