import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BolnicaComponent } from './components/main/bolnica/bolnica.component';
import { DijagnozaComponent } from './components/main/dijagnoza/dijagnoza.component';
import { OdeljenjeComponent } from './components/main/odeljenje/odeljenje.component';
import { HomeComponent } from './components/utility/home/home.component';
import { AboutComponent } from './components/utility/about/about.component';
import { AuthorComponent } from './components/utility/author/author.component';


const routes: Routes = [
  {path:'bolnica', component:BolnicaComponent},
  {path:'dijagnoza', component:DijagnozaComponent},
  {path:'odeljenje', component:OdeljenjeComponent},
  {path:'home', component:HomeComponent},
  {path:'about', component:AboutComponent},
  {path:'author', component:AuthorComponent},
  {path:'', redirectTo:'/home', pathMatch:'full'},


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
