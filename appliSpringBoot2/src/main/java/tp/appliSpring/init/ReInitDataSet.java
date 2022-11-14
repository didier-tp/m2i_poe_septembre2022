package tp.appliSpring.init;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import tp.appliSpring.core.dao.DaoOperation;
import tp.appliSpring.core.entity.Client;
import tp.appliSpring.core.entity.Compte;
import tp.appliSpring.core.entity.Operation;
import tp.appliSpring.core.service.ServiceClient;
import tp.appliSpring.core.service.ServiceCompte;

// classe qui initialise un jeu de donnees en phase de développement (sinon table vides)
@Component
@Profile("dev")
public class ReInitDataSet {
	
	@Autowired
	private ServiceCompte serviceCompte;
	
	@Autowired
	private ServiceClient serviceClient;
	
	@Autowired
	private DaoOperation daoOperation;
	
	@PostConstruct
	public void init() {
		Compte compteC1Sauvegarde = serviceCompte.sauvegarderCompte(new Compte(null,"compteC1", 50.0));
		Operation opC1a = new Operation(null, "achat bonbons", -4.67);
		opC1a.setCompte(compteC1Sauvegarde);
		daoOperation.save(opC1a);
		
		
		Compte compteC2Sauvegarde  = serviceCompte.sauvegarderCompte(new Compte(null,"compteC2", 150.0));
		Client clientA = new Client(null, "alex", "Therieur", "12 rue Elle 75001 Paris", "email1");
		clientA.getComptes().add(compteC1Sauvegarde);
		clientA.getComptes().add(compteC2Sauvegarde);
		clientA = serviceClient.sauvegarderClient(clientA);
		
	
		Compte compteC3Sauvegarde = serviceCompte.sauvegarderCompte(new Compte(null,"compteC3", 250.0));
		Compte compteC4Sauvegarde = serviceCompte.sauvegarderCompte(new Compte(null,"compteC4", 350.0));
		Client clientB = new Client(null, "axelle", "Aire", "13 rue Elle 75001 Paris", "email2");
		clientB.getComptes().add(compteC3Sauvegarde);
		clientB.getComptes().add(compteC4Sauvegarde);
		clientB = serviceClient.sauvegarderClient(clientB);
		
		Client clientABis = new Client(null, "alain", "Therieur", "12 rue Elle 75001 Paris", "email1bis");
		clientABis = serviceClient.sauvegarderClient(clientABis);
	}

}
