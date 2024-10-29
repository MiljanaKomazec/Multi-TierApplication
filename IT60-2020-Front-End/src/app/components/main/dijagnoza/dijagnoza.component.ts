import { Component, OnDestroy, OnInit, ViewChild } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { Subscription } from 'rxjs';
import { MatDialog } from '@angular/material/dialog';
import { Dijagnoza } from 'src/app/models/dijagnoza';
import { DijagnozaService } from 'src/app/service/dijagnoza.service';
import { DijagnozaDialogComponent } from '../../dialogs/dijagnoza-dialog/dijagnoza-dialog.component';
import { MatSort } from '@angular/material/sort';
import { MatPaginator } from '@angular/material/paginator';

@Component({
  selector: 'app-dijagnoza',
  templateUrl: './dijagnoza.component.html',
  styleUrls: ['./dijagnoza.component.css']
})
export class DijagnozaComponent implements OnInit, OnDestroy{
  displayedColumns = ['id', 'naziv', 'opis', 'oznaka', 'actions'];
  dataSource!:MatTableDataSource<Dijagnoza>

  subscription!:Subscription;

  @ViewChild(MatSort, {static:false}) sort!:MatSort;  
  @ViewChild(MatPaginator, {static:false}) paginator!:MatPaginator; 

  constructor(private dijagnozaService : DijagnozaService,
              public dialog:MatDialog){

  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }

  ngOnInit(): void {
    this.loadData();
  }

  public loadData(){
    this.subscription = this.dijagnozaService.getAllDijagnoza().subscribe(
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

  public openDialog(flag:number, id?:number, naziv?:string, opis?:string, oznaka?:number ):void{
    const dialogRef = this.dialog.open(DijagnozaDialogComponent, {data:{id,naziv,opis,oznaka}});
    dialogRef.componentInstance.flag=flag;
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
