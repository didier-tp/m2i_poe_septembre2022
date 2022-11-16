import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-comptes',
  templateUrl: './comptes.component.html',
  styleUrls: ['./comptes.component.css']
})
export class ComptesComponent implements OnInit {

  numClient = 0;
  listComptes :any[]= [];

  searchComptes(){
    this.listComptes.push({numero : 1 , label: "compteA" , solde : 50});
    this.listComptes.push({numero : 2 , label: "compteB" , solde : 150});
  }

  constructor() { }

  ngOnInit(): void {
  }

}
