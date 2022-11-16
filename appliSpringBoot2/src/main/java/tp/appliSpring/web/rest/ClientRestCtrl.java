package tp.appliSpring.web.rest;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tp.appliSpring.converter.DtoConverter;
import tp.appliSpring.core.entity.Client;
import tp.appliSpring.core.service.ServiceClient;
import tp.appliSpring.dto.Customer;
import tp.appliSpring.dto.Message;

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
	
	//URL= http://localhost:8080/appliSpringBoot/bank-api/client
	//appelé en mode POST avec le corps de la requête HTTP comportant
	// { "number" : null , "firstName" :  "jean" , "lastName" : "Bon" , "address" : "12 rue Xy Paris" , "email" : "jean.Bon@gmail.com"}
	@PostMapping("") 
	public Customer postCustomer(@RequestBody Customer customer) {
		Client client = DtoConverter.customerToClient(customer);//dto-->entity
		serviceClient.sauvegarderClient(client);//avec auto_incr du numero
		customer.setNumber(client.getNumero());
	    return customer;
	}
	
	//URL= http://localhost:8080/appliSpringBoot/bank-api/client
	//appelé en mode PUT avec le corps de la requête HTTP comportant
	// { "number" : 4 , "firstName" :  "jean2" , "lastName" : "Bon" , "address" : "13 rue Xy Paris" , "email" : "jean.Bon@gmail.com"}
	@PutMapping("") 
	public ResponseEntity<?> putCustomer(@RequestBody Customer customer) {
		Client client = DtoConverter.customerToClient(customer);//dto-->entity
		Client clientExistant = serviceClient.rechercherClientParNumero(customer.getNumber());
		if(clientExistant == null) {
			return new ResponseEntity<Message>( 
					new Message("impossible de mettre à jour les données du client qui n'existe pas avec le numero=" 
							+ customer.getNumber()) 
					, HttpStatus.NOT_FOUND);
		}
		else {
		serviceClient.sauvegarderClient(client);
		     return new ResponseEntity<Customer>(customer, HttpStatus.OK);
		}
	}
	
	//URL= http://localhost:8080/appliSpringBoot/bank-api/client/3
	//appelé en mode delete
	@DeleteMapping("/{numClient}")
	public ResponseEntity<Message> deleteCustomerByNum(@PathVariable("numClient") Long numClient) {
			try {
				serviceClient.supprimerClient(numClient);
				return new ResponseEntity<Message>(new Message("client bien supprimé"), HttpStatus.OK); //OK:200
				//return new ResponseEntity<Message>(new Message("client bien supprimé"), HttpStatus.NO_CONTENT); //NO_CONTENT:204
			} catch (Exception e) {
				e.printStackTrace();
				return new ResponseEntity<Message>(new Message("suppression client impossible"), HttpStatus.NOT_FOUND);
			}
		
	}

}
