package tp.appliSpring.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import tp.appliSpring.core.dao.DaoClient;
import tp.appliSpring.core.entity.Client;

//@Component
@Service //@Service signifie @Component de type service métier (business service)
public class ServiceClientImpl implements ServiceClient {
	
	//@Autowired ici ou bien implicitement sur constructeur
	//@Qualifier("daoClientJpa")
	private DaoClient daoClient;
	
	//@Autowired //@Autowired implicite si un seul constructeur
	/* ancienne version sans spring-data: public ServiceClientImpl(@Qualifier("daoClientJpa")DaoClient daoClient) { */
	public ServiceClientImpl(DaoClient daoClient) {
		this.daoClient = daoClient;
	}

	@Override
	public Client rechercherClientParNumero(long numero) {
		return daoClient.findById(numero).orElse(null);
	}

	@Override
	public List<Client> rechercherTousClients() {
		return daoClient.findAll();
	}

	

	@Override
	public Client sauvegarderClient(Client Client) {
		return daoClient.save(Client);
	}

	@Override
	public void supprimerClient(long numCpt) {
		daoClient.deleteById(numCpt);
	}

	@Override
	public Client rechercherClientAvecComptesParNumero(long numeroCli) {
		return daoClient.findWithAccountById(numeroCli);
	}

	
}
