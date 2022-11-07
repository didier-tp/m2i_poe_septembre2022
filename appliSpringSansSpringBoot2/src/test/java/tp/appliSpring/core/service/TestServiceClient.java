package tp.appliSpring.core.service;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import tp.appliSpring.core.MySpringApplication;
import tp.appliSpring.core.entity.Client;
import tp.appliSpring.core.entity.Compte;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes={MySpringApplication.class}) //java config
public class TestServiceClient {
	
	private static Logger logger = LoggerFactory.getLogger(TestServiceClient.class);
	
	@Autowired
	private ServiceClient serviceClient; //à tester
	
	@Autowired
	private ServiceCompte serviceCompte; //pour aider à tester
	
	@Test
	public void testerRechercherUnClient() {
		Compte compteX1Sauvegarde = serviceCompte.sauvegarderCompte(new Compte(null,"CompteX1",256.0));
		Compte compteX2Sauvegarde = serviceCompte.sauvegarderCompte(new Compte(null,"CompteX2",156.0));
		Client clientXy = new Client(null,"jean","Xxx","12 rue Elle 75001 Paris","email1");
		clientXy.getComptes().add(compteX1Sauvegarde);
		clientXy.getComptes().add(compteX2Sauvegarde);
		clientXy= serviceClient.sauvegarderClient(clientXy);
		//Client clientXyRelu = serviceClient.rechercherClientParNumero(clientXy.getNumero());
		Client clientXyRelu = serviceClient.rechercherClientAvecComptesParNumero(clientXy.getNumero());
		logger.debug("clientXyRelu="+clientXyRelu.toString());
		for(Compte cpt : clientXyRelu.getComptes()) {
			logger.debug("\t"+cpt);
		}
	}
	
	
	@Test
	public void testRechercherTousLesClients() {
		serviceClient.sauvegarderClient(new Client(null,"jean","Bon","12 rue Elle 75001 Paris","email1"));
		serviceClient.sauvegarderClient(new Client(null,"axelle","Air","2 rue xy 69001 Lyon","email2"));
		List<Client> clients = serviceClient.rechercherTousClients();
		logger.debug("clients="+clients);
		Assertions.assertTrue(clients!= null && clients.size()>=2);
	}
	
	@Test
	 public void testAjoutEtRelectureEtSuppression() {
			//hypothese : base avec tables vides et existantes au lancement du test
				Client client = new Client(null,"alex","Therieur","2 rue xy 75002 Paris","emailQuiVaBien");
				Client clientSauvegarde = this.serviceClient.sauvegarderClient(client); //INSERT INTO
				logger.debug("ClientSauvegarde=" + clientSauvegarde);
				Client clientRelu = this.serviceClient.rechercherClientParNumero(clientSauvegarde.getNumero()); 
				Assertions.assertEquals("alex",clientRelu.getPrenom());
				Assertions.assertEquals("Therieur",clientRelu.getNom());
				logger.debug("ClientRelu apres insertion=" + clientRelu);
				client.setPrenom("alain"); 
				Client clientMisAjour = this.serviceClient.sauvegarderClient(client); //UPDATE
				logger.debug("ClientMisAjour=" + clientMisAjour);
				clientRelu = this.serviceClient.rechercherClientParNumero(clientSauvegarde.getNumero()); //SELECT
				Assertions.assertEquals("alain",clientRelu.getPrenom());
				
				logger.debug("clientRelu apres miseAjour=" + clientRelu);
				//+supprimer :
				this.serviceClient.supprimerClient(clientSauvegarde.getNumero());
				//verifier bien supprimé (en tentant une relecture qui renvoi null)
				Client clientReluApresSuppression =
				 this.serviceClient.rechercherClientParNumero(clientSauvegarde.getNumero()); 
				Assertions.assertTrue(clientReluApresSuppression == null);
			}
	
	
}
