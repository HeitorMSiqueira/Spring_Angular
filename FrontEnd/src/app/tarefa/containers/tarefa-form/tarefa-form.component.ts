import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { NonNullableFormBuilder } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { TarefaService } from '../../services/tarefa.service';
import { Location } from '@angular/common';
import { Tarefa } from '../../model/tarefa';
import { Validators } from '@angular/forms';


@Component({
  selector: 'app-tarefa-form',
  templateUrl: './tarefa-form.component.html',
  styleUrls: ['./tarefa-form.component.css']
})
export class TarefaFormComponent implements OnInit {

  formulario = this.formBuilder.group({
    _id: [''],
    titulo: ['', [Validators.required]], //informando ao inicializar que o campo nome é uma string
    descricao: [''],
    dataHoraCriacao: [''],
    dataHoraConclusao: [''],
    excluido: false,
    status: [''],
  });

  constructor(
    private formBuilder: NonNullableFormBuilder, //força no formulario a exigir que os campos seja diferente de null
    private service: TarefaService,
    private snackBar: MatSnackBar,
    private location: Location, //para pegar a localizacao da pagina
    private route: ActivatedRoute // para pegar a rota ativa
  ) { }

  ngOnInit(): void {
     //this.formulario.value.name; //acessando o formulario
     const tarefa: Tarefa = this.route.snapshot.data['tarefaRota'];
     this.formulario.setValue({
       _id: tarefa._id, 
       titulo: tarefa.titulo,
       descricao: tarefa.descricao,
       dataHoraCriacao: tarefa.dataHoraCriacao, 
       dataHoraConclusao: tarefa.dataHoraConclusao,
       excluido: tarefa.excluido,
       status:tarefa.status
     });
  }


  onSubmit() {
    if(this.validationSubmit(this.formulario.value)){
      return;
    }
    this.service.salvar(this.formulario.value)
      .subscribe(result => this.onSuccess(), error => this.onError());
  }

  onCancel() {
    this.location.back(); // usando o location para retornar para a pagina
  }

  private onError() {
    this.snackBar.open('Não foi possível gravar', '', { duration: 3000 });
  }

  private onSuccess() {
    this.snackBar.open('Tarefa salva com sucesso', '', { duration: 3000 });
    this.location.back(); // usando o location para retornar para a pagina
  }

  private validationSubmit(formulario: any){
    if(!this.formulario.value.titulo){
      this.snackBar.open('Obrigatorio informar título', '', { duration: 3000 });
      return true;
    }
    else if(!this.formulario.value.status){
      this.snackBar.open('Obrigatório informar status', '', { duration: 3000 });
      return true;
    }
    else if(this.formulario.value.status == "Concluida" && !this.formulario.value.dataHoraConclusao){
      this.snackBar.open('Status concluída, obrigatório informar data de conclusão', '', { duration: 3000 });
      return true;
    }
    else if(this.formulario.value.dataHoraConclusao && this.formulario.value.status != "Concluida"){
      this.snackBar.open('Data de conclusão preenchida, obrigatório informar status como Concluída', '', { duration: 3000 });
      return true;
    }
    return false;
  }

}
