import { Component, OnInit } from '@angular/core';
import { Compte } from '../common/data/compte';
import { Virement } from '../common/data/virement';
import { CompteService } from '../common/service/compte.service';

@Component({
  selector: 'app-comptes',
  templateUrl: './comptes.component.html',
  styleUrls: ['./comptes.component.css']
})
export class ComptesComponent implements OnInit {

  numClient = 0;
  listComptes :Compte[]= [];
  virement :Virement = new Virement();

  declencherVirement(){
    this.compteService.postVirement$(this.virement)
    .subscribe({
       next: (virementEffectue:Virement)=>{ 
                this.postTraitementVirement(virementEffectue);
              },
       error: (err)=>{ console.log(err);}
    });
  }

  postTraitementVirement(virementEffectue:Virement){
    this.virement = virementEffectue;
    if(virementEffectue.ok){
      this.searchComptes();
    }
  }

  searchComptes(){
    /*
    //pre version sans service:
    this.listComptes.push({numero : 1 , label: "compteA" , solde : 50});
    this.listComptes.push({numero : 2 , label: "compteB" , solde : 150});
    */
    this.compteService.rechercherComptesDuClient$(this.numClient)
                       .subscribe({
                          next: (listeDeComptes:Compte[])=>{ 
                                 this.listComptes = listeDeComptes;},
                          error: (err)=>{ console.log(err);}
                       });
  }

  constructor(private compteService :CompteService) { }

  ngOnInit(): void {
  }

}
