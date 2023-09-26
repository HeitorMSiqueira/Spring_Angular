import { TarefaFormComponent } from './containers/tarefa-form/tarefa-form.component';
import { TarefaResolver } from './guards/tarefa.resolver';
import { TarefaComponent } from './containers/tarefa/tarefa.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {path: '', component:TarefaComponent},
  {path: 'new', component:TarefaFormComponent, resolve: {tarefaRota: TarefaResolver}}, // rotas
  {path: 'edit/:idTarefa', component:TarefaFormComponent, resolve: {tarefaRota: TarefaResolver}} // resolve vai para tarefa resolve para carregar a info


];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class TarefaRoutingModule { }