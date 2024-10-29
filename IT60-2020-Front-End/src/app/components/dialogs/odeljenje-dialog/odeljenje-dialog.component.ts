import { Odeljenje } from 'src/app/models/odeljenje';
import { OdeljenjeService } from './../../../service/odeljenje.service';
import { Component, Inject, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { Bolnica } from 'src/app/models/bolnica';
import { BolnicaService } from 'src/app/service/bolnica.service';

@Component({
  selector: 'app-odeljenje-dialog',
  templateUrl: './odeljenje-dialog.component.html',
  styleUrls: ['./odeljenje-dialog.component.css']
})
export class OdeljenjeDialogComponent implements OnInit{
  flag!:number;
  bolnice!:Bolnica[];

  constructor(public snackBar: MatSnackBar,
              public dialogRef: MatDialogRef<Odeljenje>,
              @Inject(MAT_DIALOG_DATA) public data: Odeljenje,
              public odeljenjeService:OdeljenjeService,
              public bolnicaService:BolnicaService){

  }


  ngOnInit(): void {
    this.bolnicaService.getAllBolnica().subscribe(
      data => {
        this.bolnice = data;
      }
    )
  }

  public compare(a:any,b:any){
    return a.id == b.id;
  }

  public add():void{
    this.odeljenjeService.addOdeljenje(this.data).subscribe(
      () => {
        this.snackBar.open('Odeljenje sa nazivom: ' + this.data.naziv + ' je uspesno kreirana',
          'ok', {duration:3500})

      }
    ),
    (error:Error) => {
      console.log(error.name + ' ' + error.message);
      this.snackBar.open('Kreiranje Odeljenja je neuspešno',
      'ok', {duration:3500});
    }
  }

  public update():void{
    this.odeljenjeService.updateOdeljenje(this.data).subscribe(
      () => {
        this.snackBar.open('Odeljenje sa ID: ' + this.data.id + ' je uspesno modifikovano',
          'ok', {duration:3500})

      }
    ),
    (error:Error) => {
      console.log(error.name + ' ' + error.message);
      this.snackBar.open('Modifikovanje Odeljenja je neuspešno',
      'ok', {duration:3500});
    }
  }

  public delete():void{
    this.odeljenjeService.deleteOdeljenje(this.data.id).subscribe(
      () => {
        this.snackBar.open('Odeljenje je uspesno obrisano',
          'ok', {duration:3500})

      }
    ),
    (error:Error) => {
      console.log(error.name + ' ' + error.message);
      this.snackBar.open('Brisanje Odeljenja je neuspešno',
      'ok', {duration:3500});
    }
  }

  public cancel():void{
    this.dialogRef.close();
    this.snackBar.open('Odustali ste od izmena',
      'ok', {duration:3500});
  }


}
