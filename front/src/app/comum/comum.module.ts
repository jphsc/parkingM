import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CardLateralComponent } from './card-lateral/card-lateral.component';
import { CardCabecalhoComponent } from './card-cabecalho/card-cabecalho.component';
import { AppRoutingModule } from '../app-routing.module';


@NgModule({
  declarations: [
    CardLateralComponent,
    CardCabecalhoComponent
  ],
  imports: [
    CommonModule,
    AppRoutingModule
  ],
  exports: [
    CardCabecalhoComponent,
    CardLateralComponent
  ]
})
export class ComumModule { }
