package tp.appliSpring.core.service;

import java.util.Date;
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
import tp.appliSpring.core.dao.DaoOperation;
import tp.appliSpring.core.entity.Client;
import tp.appliSpring.core.entity.Compte;
import tp.appliSpring.core.entity.Operation;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes={MySpringApplication.class}) //java config
public class TestServiceCompte {
	
	private static Logger logger = LoggerFactory.getLogger(TestServiceCompte.class);
	
	@Autowired
	private ServiceCompte serviceCompte; //à tester
	
	
	@Autowired
	private ServiceClient serviceClient; //pour aider à tester
	
	@Autowired
	private DaoOperation daoOperation; //pour aider à tester
	
	/*
	@BeforeEach //ou bien @BeforeAll
	public void initialisation() {
		AnnotationConfigApplicationContext springContext = new
			    AnnotationConfigApplicationContext(MySpringApplication.class) ;
			
			this.serviceCompte  = springContext.getBean(ServiceCompte.class);
	}
	*/
	
	@Test
	public void testRechercherTousLesCompte() {
		serviceCompte.sauvegarderCompte(new Compte(null,"CompteZ1",256.0));
		serviceCompte.sauvegarderCompte(new Compte(null,"CompteZ2",156.0));
		List<Compte> comptes = serviceCompte.rechercherTousComptes();
		logger.debug("comptes="+comptes);
	}
	
	@Test
	public void testRechercherComptesDunClient() {
	
		Compte compteA1Sauvegarde = serviceCompte.sauvegarderCompte(new Compte(null,"CompteA1",256.0));
		Compte compteA2Sauvegarde = serviceCompte.sauvegarderCompte(new Compte(null,"CompteA2",156.0));
		Client clientA = new Client(null,"aaa","HaHa","12 rue Elle 75001 Paris","email1");
		clientA.getComptes().add(compteA1Sauvegarde);
		clientA.getComptes().add(compteA2Sauvegarde);
		clientA = serviceClient.sauvegarderClient(clientA);
	
		
		Compte compteB1Sauvegarde = serviceCompte.sauvegarderCompte(new Compte(null,"CompteB1",236.0));
		Compte compteB2Sauvegarde = serviceCompte.sauvegarderCompte(new Compte(null,"CompteB2",136.0));
		Client clientB = new Client(null,"bbb","BeBe","12 rue Elle 75002 Paris","email2");
		clientB.getComptes().add(compteB1Sauvegarde);
		clientB.getComptes().add(compteB2Sauvegarde);
		clientB = serviceClient.sauvegarderClient(clientB);
		
		List<Compte> comptesDuClientA = serviceCompte.rechercherComptesDuClient(clientA.getNumero());
		logger.debug("comptesDuClientA="+comptesDuClientA);
		Assertions.assertTrue(comptesDuClientA.size()==2);
		
		List<Compte> comptesDuClientB = serviceCompte.rechercherComptesDuClient(clientB.getNumero());
		logger.debug("comptesDuClientB="+comptesDuClientB);
	}
	
	@Test
	//@Order(1)
	public void testRechercherCompte() {
		Compte compteXy = new Compte(null,"CompteXy",200.0);
		serviceCompte.sauvegarderCompte(compteXy);
		Long numCompteXy = compteXy.getNumero(); //numero auto incrémenté
		
		Operation opXy1 = new Operation(null,"achat bonbons" , -4.67 ); opXy1.setCompte(compteXy);
		daoOperation.save(opXy1);
		
		Operation opXy2 = new Operation(null,"achat gateau" , -14.67 ); opXy2.setCompte(compteXy);
		daoOperation.save(opXy2);
		
		//Compte compteXyRelu = serviceCompte.rechercherCompteParNumero(numCompteXy);
		Compte compteXyRelu = serviceCompte.rechercherCompteAvecOperationsParNumero(numCompteXy);
		logger.debug("compteXyRelu="+compteXyRelu);
		Assertions.assertTrue(compteXyRelu.getNumero()==numCompteXy);
		logger.debug("operations du compte compteXy:");
		for(Operation op : compteXyRelu.getOperations()) {
			logger.debug("\t"+op);
		}
		
	}
	
	@Test
	//@Order(2)
	 public void testAjoutEtRelectureEtSuppression() {
	//hypothese : base avec tables vides et existantes au lancement du test
		Compte compte = new Compte(null,"compteA",100.0);
		Compte compteSauvegarde = this.serviceCompte.sauvegarderCompte(compte); //INSERT INTO
		logger.debug("compteSauvegarde=" + compteSauvegarde);
		Compte compteRelu = this.serviceCompte.rechercherCompteParNumero(compteSauvegarde.getNumero()); 
		Assertions.assertEquals("compteA",compteRelu.getLabel());
		Assertions.assertEquals(100.0,compteRelu.getSolde());
		logger.debug("compteRelu apres insertion=" + compteRelu);
		compte.setSolde(150.0); compte.setLabel("compte_a");
		Compte compteMisAjour = this.serviceCompte.sauvegarderCompte(compte); //UPDATE
		logger.debug("compteMisAjour=" + compteMisAjour);
		compteRelu = this.serviceCompte.rechercherCompteParNumero(compteSauvegarde.getNumero()); //SELECT
		Assertions.assertEquals("compte_a",compteRelu.getLabel());
		Assertions.assertEquals(150.0,compteRelu.getSolde());
		logger.debug("compteRelu apres miseAjour=" + compteRelu);
		//+supprimer :
		this.serviceCompte.supprimerCompte(compteSauvegarde.getNumero());
		//verifier bien supprimé (en tentant une relecture qui renvoi null)
		Compte compteReluApresSuppression =
		 this.serviceCompte.rechercherCompteParNumero(compteSauvegarde.getNumero()); 
		Assertions.assertTrue(compteReluApresSuppression == null);
		}

}
