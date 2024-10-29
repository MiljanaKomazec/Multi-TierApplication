import { Component, Inject, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { Dijagnoza } from 'src/app/models/dijagnoza';
import { Pacijent } from 'src/app/models/pacijent';
import { PacijentService } from 'src/app/service/pacijent.service';
import { DijagnozaService } from 'src/app/service/dijagnoza.service';

@Component({
  selector: 'app-pacijent-dialog',
  templateUrl: './pacijent-dialog.component.html',
  styleUrls: ['./pacijent-dialog.component.css']
})
export class PacijentDialogComponent implements OnInit{
  flag!:number;
  dijagnoze!:Dijagnoza[];

  constructor(public snackBar: MatSnackBar,
              public dialogRef: MatDialogRef<Pacijent>,
              @Inject(MAT_DIALOG_DATA) public data: Pacijent,
              public pacijentService:PacijentService,
              public dijagnozaService:DijagnozaService){

  }


  ngOnInit(): void {
    this.dijagnozaService.getAllDijagnoza().subscribe(
      data => {
        this.dijagnoze = data;
      }
    )
  }

  public compare(a:any,b:any){
    return a.id = b.id;
  }

  public add():void{
    this.pacijentService.addPacijent(this.data).subscribe(
      () => {
        this.snackBar.open('Pacijent sa imenom: ' + this.data.ime + ' je uspesno kreiran',
          'ok', {duration:3500})

      }
    ),
    (error:Error) => {
      console.log(error.name + ' ' + error.message);
      this.snackBar.open('Kreiranje Pacijenta je neuspešno',
      'ok', {duration:3500});
    }
  }

  public update():void{
    this.pacijentService.updatePacijent(this.data).subscribe(
      () => {
        this.snackBar.open('Pacijent sa ID: ' + this.data.id + ' je uspesno modifikovan',
          'ok', {duration:3500})

      }
    ),
    (error:Error) => {
      console.log(error.name + ' ' + error.message);
      this.snackBar.open('Modifikovanje Pacijenta je neuspešno',
      'ok', {duration:3500});
    }
  }

  public delete():void{
    this.pacijentService.deletePacijent(this.data.id).subscribe(
      () => {
        this.snackBar.open('Pacijent je uspesno obrisan',
          'ok', {duration:3500})

      }
    ),
    (error:Error) => {
      console.log(error.name + ' ' + error.message);
      this.snackBar.open('Brisanje Pacijenta je neuspešno',
      'ok', {duration:3500});
    }
  }

  public cancel():void{
    this.dialogRef.close();
    this.snackBar.open('Odustali ste od izmena',
      'ok', {duration:3500});
  }


}


