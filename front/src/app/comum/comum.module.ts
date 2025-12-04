import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CardLateralComponent } from './card-lateral/card-lateral.component';
import { CardPrincipalComponent } from './card-principal/card-principal.component';
import { CardCabecalhoComponent } from './card-cabecalho/card-cabecalho.component';


@NgModule({
  declarations: [
    CardLateralComponent,
    CardPrincipalComponent,
    CardCabecalhoComponent
  ],
  imports: [
    CommonModule
  ],
  exports: [
    CardCabecalhoComponent,
    CardLateralComponent,
    CardPrincipalComponent
  ]
})
export class ComumModule { }
