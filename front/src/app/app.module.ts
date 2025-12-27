import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MovVeiculoModule } from './components/paginas/mov-veiculo/mov-veiculo.module';
import { RegrafFinanceiraModule } from './components/paginas/regra-financeira/regra-financeira.module';
import { VeiculoModule } from './components/paginas/veiculo/veiculo.module';
import { HomeComponent } from './components/paginas/home/home.component';
import { NaoEncontradaComponent } from './components/paginas/nao-encontrada/nao-encontrada.component';
import { ComumModule } from './components/comum/comum.module';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { LoadingInterceptor } from './services/loading.interceptor';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    NaoEncontradaComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ComumModule,
    MovVeiculoModule,
    RegrafFinanceiraModule,
    VeiculoModule,
    ReactiveFormsModule
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: LoadingInterceptor, multi: true}
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
