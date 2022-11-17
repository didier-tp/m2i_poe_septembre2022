import { Component, OnInit } from '@angular/core';
import { Client } from '../common/data/client';
import { ClientService } from '../common/service/client.service';

@Component({
  selector: 'app-client',
  templateUrl: './client.component.html',
  styleUrls: ['./client.component.css']
})
export class ClientComponent implements OnInit {

  client = new Client();
  message ="";

  constructor(private clientService : ClientService) { }

  ajoutClient(){
    this.clientService.postClient$(this.client)
    .subscribe({
       next: (client:Client)=>{ 
                this.postTraitementClient(client);
              },
       error: (err)=>{  console.log(err);
                      this.message="echec recup client" }
    });
  }

  postTraitementClient(client: Client){
    this.client = client;
    this.message="client ajouté coté serveur";
  }

  ngOnInit(): void {
  }

}
