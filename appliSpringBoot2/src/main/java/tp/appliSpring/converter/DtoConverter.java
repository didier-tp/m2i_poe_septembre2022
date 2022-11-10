package tp.appliSpring.converter;

import java.util.List;
import java.util.stream.Collectors;

import tp.appliSpring.core.entity.Client;
import tp.appliSpring.dto.Customer;

public class DtoConverter {
	
	//clientEntityToCustomerDto
	public static Customer clientToCustomer(Client client) {
		return new Customer(client.getNumero(),
				            client.getPrenom(),
				            client.getNom(),
				            client.getEmail(),
				            client.getAdresse());
	}
	
	public static List<Customer> clientListToCustomerList(List<Client> clients){
		return clients.stream()
			   .map((client)->clientToCustomer(client))
			   .collect(Collectors.toList());
	}

}
