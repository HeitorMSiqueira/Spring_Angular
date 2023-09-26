import { TarefaService } from './../services/tarefa.service';
import { Injectable } from '@angular/core';
import { Router, Resolve, RouterStateSnapshot,  ActivatedRouteSnapshot } from '@angular/router';
import { Observable, of } from 'rxjs';
import { Tarefa } from '../model/tarefa';

@Injectable({
  providedIn: 'root'
})
export class TarefaResolver implements Resolve<Tarefa> {

  constructor(private service: TarefaService){}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Tarefa> {
    //guarda de rotas
    //se possuir id vai retornar o objeto de acordo com o id ou vai inicializar com um objeto vazio
    //se for uma edicao vai pegar o id do parametro e vai chamar o servico para carregar a informacao da tarefa atraves da api do spring
    if(route.params && route.params['idTarefa']){
      return this.service.findById(route.params['idTarefa']);
    }
    //se for a roda de criacao vai retornar o objeto vazio
    return of({_id: '', titulo: '', descricao: '', dataHoraCriacao: '', dataHoraConclusao: '', excluido: false, status: ''});
  }
}
