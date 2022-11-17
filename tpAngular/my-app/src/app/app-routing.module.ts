import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ComptesComponent } from './comptes/comptes.component';
import { WelcomeComponent } from './welcome/welcome.component';

const routes: Routes = [
  { path: 'welcome', component: WelcomeComponent }, 
  { path: '', redirectTo: '/welcome', pathMatch: 'full'},
  { path: 'comptes', component: ComptesComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
