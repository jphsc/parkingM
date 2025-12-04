import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { RegraFinanceiraRoutingModule } from './regra-financeira-routing.module';
import { RegraListarComponent } from './regra-listar/regra-listar.component';
import { RegraFormComponent } from './regra-form/regra-form.component';
import { RegraDetalheComponent } from './regra-detalhe/regra-detalhe.component';


@NgModule({
  declarations: [
    RegraListarComponent,
    RegraFormComponent,
    RegraDetalheComponent
  ],
  imports: [
    CommonModule,
    RegraFinanceiraRoutingModule
  ]
})
export class RegrafFinanceiraModule { }
