import { Tarefa } from './../../model/tarefa';
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';

@Component({
  selector: 'app-tarefa-list',
  templateUrl: './tarefa-list.component.html',
  styleUrls: ['./tarefa-list.component.css']
})
export class TarefaListComponent implements OnInit {

  @Input() tarefaList: Tarefa[] = []; //input é o que vem para o componente
  @Output() adicionar = new EventEmitter(false); //o que esta saindo os eventos -- para adicionar
  @Output() editar = new EventEmitter(false); //o que esta saindo os eventos -- para editar
  @Output() excluir = new EventEmitter(false);
 
   readonly displayedColumns = ['titulo', 'descricao', 'status', 'botoes']; //colunas que serao mostradas no html

  constructor() { }

  ngOnInit(): void {
  }

  //ROTEAMENTO
  onAdd(){
    this.adicionar.emit(true);
  }

  onEdit(tarefa: Tarefa){
    this.editar.emit(tarefa);
  }

  onDelete(tarefa: Tarefa){
    this.excluir.emit(tarefa);
  }

}
