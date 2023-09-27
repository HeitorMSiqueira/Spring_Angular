import { AppMaterialModule } from './app-material/app-material.module';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ErrorDialogComponent } from './components/error-dialog/error-dialog.component';
import { StatusPipe } from './pipes/status.pipe';



@NgModule({
  declarations: [
    ErrorDialogComponent,
    StatusPipe
  ],
  imports: [
    CommonModule,
    AppMaterialModule
  ],
  exports:[
    ErrorDialogComponent,
    StatusPipe
  ]
})
export class SharedModule { }
