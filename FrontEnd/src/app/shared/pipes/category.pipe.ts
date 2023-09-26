import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'category'
})
export class CategoryPipe implements PipeTransform {
  
  transform(value: string): string {
    switch(value){
      case 'Concluída': return 'concluída';
      case 'Pendente': return 'pendente';
    }
    return 'code';
  }

}
