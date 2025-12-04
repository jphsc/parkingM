import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ComumModule } from './comum/comum.module';
import { MovVeiculoModule } from './paginas/mov-veiculo/mov-veiculo.module';
import { RegrafFinanceiraModule } from './paginas/regra-financeira/regra-financeira.module';
import { VeiculoModule } from './paginas/veiculo/veiculo.module';
import { HomeComponent } from './paginas/home/home.component';
import { NaoEncontradaComponent } from './paginas/nao-encontrada/nao-encontrada.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    NaoEncontradaComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ComumModule,
    MovVeiculoModule,
    RegrafFinanceiraModule,
    VeiculoModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
