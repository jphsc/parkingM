import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CardLateralComponent } from './card-lateral/card-lateral.component';
import { CardCabecalhoComponent } from './card-cabecalho/card-cabecalho.component';
import { AppRoutingModule } from '../../app-routing.module';
import { LoadingComponent } from './loading/loading.component';


@NgModule({
  declarations: [
    CardLateralComponent,
    CardCabecalhoComponent,
    LoadingComponent
  ],
  imports: [
    CommonModule,
    AppRoutingModule
  ],
  exports: [
    CardCabecalhoComponent,
    CardLateralComponent,
    LoadingComponent
  ]
})
export class ComumModule { }
