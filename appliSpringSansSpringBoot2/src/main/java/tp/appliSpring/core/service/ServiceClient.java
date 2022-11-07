package tp.appliSpring.core.service;

import java.util.List;
import tp.appliSpring.core.entity.Client;

public interface ServiceClient {
	Client rechercherClientParNumero(long numero);
	List<Client> rechercherTousClients();
	Client sauvegarderClient(Client Client);
	void supprimerClient(long numCpt);
}