import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Tarefa } from './../model/tarefa';
import { delay, first, Observable, tap } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class TarefaService {

  private readonly API = '/api/tarefas'; //caminho da api no SPRING

  constructor(public httpCliente: HttpClient) {}

  listaTarefa() {
    return this.httpCliente.get<Tarefa[]>(this.API)
      .pipe(
        first(),
        delay(3000),
        tap(tarefa => console.log(tarefa))
      );
  }

  findById(id: string) {
    return this.httpCliente.get<Tarefa>(`${this.API}/${id}`);
  }
  //Partial informa que eu posso enviar as informações esperadas, mesmo faltando algum campo por exemplo Id
  salvar(gravar: Partial<Tarefa>) {
    if (gravar._id) {
      return this.update(gravar);
    }
    return this.create(gravar);
  }

  private create(gravar: Partial<Tarefa>) {
    return this.httpCliente.post<Tarefa>(this.API, gravar);
  }

  private update(gravar: Partial<Tarefa>) {
    return this.httpCliente.put<Tarefa>(`${this.API}/${gravar._id}`, gravar);
  }

  excluir(id: string) {
    console.log('aaaaaa')
    return this.httpCliente.delete(`${this.API}/${id}`).pipe(first());
  }
}
