import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './paginas/home/home.component';
import { NaoEncontradaComponent } from './paginas/nao-encontrada/nao-encontrada.component';

const routes: Routes = [
  {path: '', redirectTo: 'home', pathMatch: 'full'},
  {path: 'home', component: HomeComponent},
  {path: 'veiculo', loadChildren: () => import('./paginas/veiculo/veiculo.module').then(m => m.VeiculoModule)},
  {path: 'movimento', loadChildren: () => import('./paginas/mov-veiculo/mov-veiculo.module').then(m => m.MovVeiculoModule)},
  {path: 'regra', loadChildren: () => import('./paginas/regra-financeira/regra-financeira.module').then(m => m.RegrafFinanceiraModule)},
  {path: '**', component: NaoEncontradaComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
