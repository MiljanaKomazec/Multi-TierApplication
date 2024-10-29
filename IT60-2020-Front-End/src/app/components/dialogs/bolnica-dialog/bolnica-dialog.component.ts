import { Bolnica } from 'src/app/models/bolnica';
import { BolnicaService } from './../../../service/bolnica.service';
import { Component, Inject } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-bolnica-dialog',
  templateUrl: './bolnica-dialog.component.html',
  styleUrls: ['./bolnica-dialog.component.css']
})
export class BolnicaDialogComponent {

  flag!:number;
  constructor(public snackBar: MatSnackBar,
              public dialogRef: MatDialogRef<Bolnica>,
              @Inject(MAT_DIALOG_DATA) public data: Bolnica,
              public bolnicaService:BolnicaService){

  }

  public add():void{
    this.bolnicaService.addBolnica(this.data).subscribe(
      () => {
        this.snackBar.open('Bolnica sa nazivom: ' + this.data.naziv + ' je uspesno kreirana',
          'ok', {duration:3500})

      }
    ),
    (error:Error) => {
      console.log(error.name + ' ' + error.message);
      this.snackBar.open('Kreiranje Bolnice je neuspešno',
      'ok', {duration:3500});
    }
  }

  public update():void{
    this.bolnicaService.updateBolnica(this.data).subscribe(
      () => {
        this.snackBar.open('Bolnica sa ID: ' + this.data.id + ' je uspesno modifikovana',
          'ok', {duration:3500})

      }
    ),
    (error:Error) => {
      console.log(error.name + ' ' + error.message);
      this.snackBar.open('Modifikovanje Bolnice je neuspešno',
      'ok', {duration:3500});
    }
  }

  public delete():void{
    this.bolnicaService.deleteBolnica(this.data.id).subscribe(
      () => {
        this.snackBar.open('Bolnica je uspesno obrisana',
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
