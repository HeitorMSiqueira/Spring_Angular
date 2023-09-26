import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [

  {path: '', pathMatch: 'full', redirectTo: 'tarefa'},
  {
    path: 'tarefa', loadChildren: () => import('./tarefa/tarefa.module').then(m => m.TarefaModule)
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
