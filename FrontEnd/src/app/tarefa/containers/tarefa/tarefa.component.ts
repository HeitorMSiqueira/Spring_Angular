import { catchError, Observable, of } from 'rxjs';
import { Component, OnInit } from '@angular/core';
import { TarefaService } from '../../services/tarefa.service';
import { Tarefa } from '../../model/tarefa';
import { MatDialog } from '@angular/material/dialog';
import { ErrorDialogComponent } from 'src/app/shared/components/error-dialog/error-dialog.component';
import { ActivatedRoute, Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-tarefa',
  templateUrl: './tarefa.component.html',
  styleUrls: ['./tarefa.component.css']
})
export class TarefaComponent implements OnInit {

  tarefaList$: Observable<Tarefa[]> | null = null;

  constructor(
    private tarefaService: TarefaService,
    public dialog: MatDialog,
    private router: Router,
    private route: ActivatedRoute,
    private snackBar: MatSnackBar
  ) { 
     //CARREGAMENTO DAS INFORMAÇOES
     this.refresh();
  }

  refresh() {
    this.tarefaList$ = this.tarefaService.listaTarefa()
      .pipe(
        catchError(error => {
          this.onError('Erro ao carregar tarefas.')
          return of([])
        })
      );
  }

  onError(errorMsg: string) {
    this.dialog.open(ErrorDialogComponent, {
      data: errorMsg
    });
  }

   //ROTEAMENTO
   onAdd() {
    this.router.navigate(['new'], { relativeTo: this.route }) //faz a rota quando clicar no botao NEW
  }

  onEdit(tarefa: Tarefa) {
    this.router.navigate(['edit', tarefa._id], { relativeTo: this.route }) //faz a rota quando clicar no botao NEW
  }

  onDelete(tarefa: Tarefa) {
    this.tarefaService.excluir(tarefa._id).subscribe(
      () => {
        this.refresh();
        this.snackBar.open('Tarefa removido com sucesso!', 'X', {
          duration: 5000,
          verticalPosition: 'top',
          horizontalPosition: 'center'
        });
      },
      () => this.onError('Erro ao tentar remover tarefa.')
    );
  }

  ngOnInit(): void {
  }
}
