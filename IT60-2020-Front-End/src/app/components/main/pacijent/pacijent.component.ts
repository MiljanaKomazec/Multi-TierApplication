import { Component, Input, OnInit, OnDestroy, OnChanges, SimpleChanges, ViewChild } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { Subscription } from 'rxjs';
import { MatDialog } from '@angular/material/dialog';
import { Pacijent } from 'src/app/models/pacijent';
import { PacijentService } from 'src/app/service/pacijent.service';
import { Dijagnoza } from 'src/app/models/dijagnoza';
import { PacijentDialogComponent } from '../../dialogs/pacijent-dialog/pacijent-dialog.component';
import { Odeljenje } from 'src/app/models/odeljenje';
import { MatSort } from '@angular/material/sort';
import { MatPaginator } from '@angular/material/paginator';


@Component({
  selector: 'app-pacijent',
  templateUrl: './pacijent.component.html',
  styleUrls: ['./pacijent.component.css']
})
export class PacijentComponent implements OnInit,OnDestroy,OnChanges {

  displayedColumns = ['id', 'ime', 'prezime', 'zdr_osiguranje', 'datum_rodjenja' ,'dijagnoza', 'actions'];
  dataSource!: MatTableDataSource<Pacijent>;

  subscription!:Subscription;

  @Input() childSelectedOdeljenje!: Odeljenje;

  @ViewChild(MatSort, {static:false}) sort!:MatSort;  
  @ViewChild(MatPaginator, {static:false}) paginator!:MatPaginator;

  constructor(private pacijentService : PacijentService,
              public dialog:MatDialog){

  }

  ngOnChanges(changes: SimpleChanges): void {
    if(this.childSelectedOdeljenje.id){
      this.loadData();
    }
  }
  
  

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }

  ngOnInit(): void {
    this.loadData();
  }

  public loadData(){
    this.subscription = this.pacijentService.getPacijentiForOdeljenje(this.childSelectedOdeljenje.id).subscribe(
      data => {
        console.log(data);
        this.dataSource = new MatTableDataSource(data);
        this.dataSource.sort = this.sort;
        this.dataSource.paginator = this.paginator;
      }
      
      
      ), 
      
        (error:Error) => {console.log(error.name + ' ' + error.message);
    };
    
  }

  public openDialog(flag:number, id?:number, ime?:string, prezime?:string, zdr_osiguranje?:boolean, datum_rodjenja?:Date, dijagnoza?:Dijagnoza):void{
    const dialogRef = this.dialog.open(PacijentDialogComponent, {data:{id,ime,prezime, zdr_osiguranje, datum_rodjenja, dijagnoza}});
    dialogRef.componentInstance.flag=flag;
    dialogRef.componentInstance.data.odeljenje = this.childSelectedOdeljenje;
    dialogRef.afterClosed().subscribe(
      result =>{
        if(result==1){
          this.loadData();
        }
      }
    )

  }

  public applyFilter(filter:any){
    filter = filter.target.value;
    filter = filter.trim();
    filter = filter.toLocaleLowerCase();
    this.dataSource.filter = filter;

  }

}
