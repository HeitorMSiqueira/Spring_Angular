import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'status'
})
export class StatusPipe implements PipeTransform {
  
  transform(value: string): string {
    switch(value){
      case 'Concluída': return 'concluída';
      case 'Pendente': return 'pendente';
    }
    return '';
  }

}
