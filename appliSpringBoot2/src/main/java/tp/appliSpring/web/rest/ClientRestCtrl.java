package tp.appliSpring.web.rest;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tp.appliSpring.converter.DtoConverter;
import tp.appliSpring.core.entity.Client;
import tp.appliSpring.core.service.ServiceClient;
import tp.appliSpring.dto.Customer;

@RestController //composant spring de type contrôleur pour Web Service REST
@RequestMapping(value="/bank-api/client" , headers="Accept=application/json")
public class ClientRestCtrl {
	
	@Autowired
	private ServiceClient serviceClient;
	
	//URL= http://localhost:8080/appliSpringBoot/bank-api/client/1
	@GetMapping("/{numClient}")
	public Customer getCustomerByNum(@PathVariable("numClient") Long numClient) {
		Client clientEntity = serviceClient.rechercherClientParNumero(numClient);
		Customer customerDto = DtoConverter.clientToCustomer(clientEntity);
		return customerDto;
	}
	
	//URL= http://localhost:8080/appliSpringBoot/bank-api/client
	//ou   http://localhost:8080/appliSpringBoot/bank-api/client?nom=Therieur
	@GetMapping("")
	public List<Customer> getCustomersByCriteria(
			@RequestParam(name="nom" , required=false)String nomClient){
		if(nomClient==null)
		   return DtoConverter.clientListToCustomerList(serviceClient.rechercherTousClients());
		else {
		   List<Client> clients=null;
		   //clients = serviceClient.rechercherClientsParNom(nomClient);
		   clients = serviceClient.rechercherTousClients().stream()
				  .filter((client)->client.getNom().equals(nomClient))
				  .collect(Collectors.toList());
		   List<Customer> customers=DtoConverter.clientListToCustomerList(clients);
		   return customers;
		}
	}

}