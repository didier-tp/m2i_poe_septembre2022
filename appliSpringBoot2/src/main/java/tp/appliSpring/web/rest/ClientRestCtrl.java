package tp.appliSpring.web.rest;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tp.appliSpring.core.entity.Client;
import tp.appliSpring.core.service.ServiceClient;

@RestController //composant spring de type contrôleur pour Web Service REST
@RequestMapping(value="/bank-api/client" , headers="Accept=application/json")
public class ClientRestCtrl {
	
	@Autowired
	private ServiceClient serviceClient;
	
	//URL= http://localhost:8080/appliSpringBoot/bank-api/client/1
	@GetMapping("/{numClient}")
	public Client getClientByNum(@PathVariable("numClient") Long numClient) {
		return serviceClient.rechercherClientParNumero(numClient);
	}
	
	//URL= http://localhost:8080/appliSpringBoot/bank-api/client
	//ou   http://localhost:8080/appliSpringBoot/bank-api/client?nom=Therieur
	@GetMapping("")
	public List<Client> getClientsByCriteria(
			@RequestParam(name="nom" , required=false)String nomClient){
		if(nomClient==null)
		   return serviceClient.rechercherTousClients();
		else {
		   //return serviceClient.rechercherClientsParNom(nomClient);
		   return serviceClient.rechercherTousClients().stream()
				  .filter((client)->client.getNom().equals(nomClient))
				  .collect(Collectors.toList());
		}
	}

}
