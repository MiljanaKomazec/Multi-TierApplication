import { OdeljenjeDialogComponent } from './../../dialogs/odeljenje-dialog/odeljenje-dialog.component';
import { Odeljenje } from './../../../models/odeljenje';
import { Component, OnDestroy, OnInit, ViewChild } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { Subscription } from 'rxjs';
import { MatDialog } from '@angular/material/dialog';
import { OdeljenjeService } from 'src/app/service/odeljenje.service';
import { Bolnica } from 'src/app/models/bolnica';
import { MatSort } from '@angular/material/sort';
import { MatPaginator } from '@angular/material/paginator';

@Component({
  selector: 'app-odeljenje',
  templateUrl: './odeljenje.component.html',
  styleUrls: ['./odeljenje.component.css']
})
export class OdeljenjeComponent implements OnInit, OnDestroy {
  displayedColumns = ['id', 'naziv', 'lokacija', 'bolnica', 'actions'];
  dataSource!:MatTableDataSource<Odeljenje>

  subscription!:Subscription;

  @ViewChild(MatSort, {static:false}) sort!:MatSort;  
  @ViewChild(MatPaginator, {static:false}) paginator!:MatPaginator; 

  parentSelectedOdeljenje!:Odeljenje;

  constructor(private odeljenjeService : OdeljenjeService,
              public dialog:MatDialog){

  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }

  ngOnInit(): void {
    this.loadData();
  }

  public loadData(){
    this.subscription = this.odeljenjeService.getAllOdeljenje().subscribe(
      data => {
        //console.log(data);
        this.dataSource = new MatTableDataSource(data)
        this.dataSource.sort = this.sort;
        this.dataSource.paginator = this.paginator;
      }
      
      
      ), 
      
        (error:Error) => {console.log(error.name + ' ' + error.message);
    };
    
  }

  public openDialog(flag:number, id?:number, naziv?:string, lokacija?:string, bolnica?:Bolnica ):void{
    const dialogRef = this.dialog.open(OdeljenjeDialogComponent, {data:{id,naziv,lokacija,bolnica}});
    dialogRef.componentInstance.flag=flag;
    dialogRef.afterClosed().subscribe(
      result =>{
        if(result==1){
          this.loadData();
        }
      }
    )

  }

  public selectRow(row:Odeljenje):void{
    this.parentSelectedOdeljenje = row;

  }

  public applyFilter(filter:any){
    filter = filter.target.value;
    filter = filter.trim();
    filter = filter.toLocaleLowerCase();
    this.dataSource.filter = filter;

  }

  

}
