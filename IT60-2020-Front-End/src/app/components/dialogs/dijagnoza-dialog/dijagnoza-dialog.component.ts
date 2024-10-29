import { Component, Inject, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { Dijagnoza } from 'src/app/models/dijagnoza';
import { DijagnozaService } from 'src/app/service/dijagnoza.service';

@Component({
  selector: 'app-dijagnoza-dialog',
  templateUrl: './dijagnoza-dialog.component.html',
  styleUrls: ['./dijagnoza-dialog.component.css']
})
export class DijagnozaDialogComponent{
  flag!:number;
  constructor(public snackBar: MatSnackBar,
              public dialogRef: MatDialogRef<Dijagnoza>,
              @Inject(MAT_DIALOG_DATA) public data: Dijagnoza,
              public dijagnozaService:DijagnozaService){

  }


  public add():void{
    this.dijagnozaService.addDijagnoza(this.data).subscribe(
      () => {
        this.snackBar.open('Dijagnoza sa nazivom: ' + this.data.naziv + ' je uspesno kreirana',
          'ok', {duration:3500})

      }
    ),
    (error:Error) => {
      console.log(error.name + ' ' + error.message);
      this.snackBar.open('Kreiranje Dijagnoze je neuspešno',
      'ok', {duration:3500});
    }
  }

  public update():void{
    this.dijagnozaService.updateDijagnoza(this.data).subscribe(
      () => {
        this.snackBar.open('Dijagnoza sa ID: ' + this.data.id + ' je uspesno modifikovana',
          'ok', {duration:3500})

      }
    ),
    (error:Error) => {
      console.log(error.name + ' ' + error.message);
      this.snackBar.open('Modifikovanje Dijagnoze je neuspešno',
      'ok', {duration:3500});
    }
  }

  public delete():void{
    this.dijagnozaService.deleteDijagnoza(this.data.id).subscribe(
      () => {
        this.snackBar.open('Dijagnoza je uspesno obrisana',
          'ok', {duration:3500})

      }
    ),
    (error:Error) => {
      console.log(error.name + ' ' + error.message);
      this.snackBar.open('Brisanje Bolnice je neuspešno',
      'ok', {duration:3500});
    }
  }

  public cancel():void{
    this.dialogRef.close();
    this.snackBar.open('Odustali ste od izmena',
      'ok', {duration:3500});
  }

}



