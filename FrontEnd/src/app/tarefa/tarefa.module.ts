import { SharedModule } from './../shared/shared.module';
import { AppMaterialModule } from './../shared/app-material/app-material.module';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { TarefaRoutingModule } from './tarefa-routing.module';
import { TarefaComponent } from './containers/tarefa/tarefa.component';
import { TarefaFormComponent } from './containers/tarefa-form/tarefa-form.component';
import { ReactiveFormsModule } from '@angular/forms';
import { TarefaListComponent } from './components/tarefa-list/tarefa-list.component';


@NgModule({
  declarations: [
    TarefaComponent,
    TarefaFormComponent,
    TarefaListComponent
  ],
  imports: [
    CommonModule,
    TarefaRoutingModule,
    AppMaterialModule, //modulo criado com os imports do angular MATERIAL para nao precisar importa a cada modulo criado (item a item).
    SharedModule,
    ReactiveFormsModule //modulo para criar formulario fornece o formbuilder e o formgroup.
  ]
})
export class TarefaModule { }
