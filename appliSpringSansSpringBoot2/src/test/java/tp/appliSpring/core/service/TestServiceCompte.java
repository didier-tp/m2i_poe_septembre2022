package tp.appliSpring.core.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import tp.appliSpring.core.MySpringApplication;
import tp.appliSpring.core.entity.Compte;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes={MySpringApplication.class}) //java config
public class TestServiceCompte {
	
	private static Logger logger = LoggerFactory.getLogger(TestServiceCompte.class);
	
	@Autowired
	private ServiceCompte serviceCompte; //à tester
	
	/*
	@BeforeEach //ou bien @BeforeAll
	public void initialisation() {
		AnnotationConfigApplicationContext springContext = new
			    AnnotationConfigApplicationContext(MySpringApplication.class) ;
			
			this.serviceCompte  = springContext.getBean(ServiceCompte.class);
	}
	*/
	
	@Test
	//@Order(1)
	public void testRechercherCompte() {
		Compte compte1 = new Compte(null,"CompteXy",200.0);
		serviceCompte.sauvegarderCompte(compte1);
		Long numCompte1 = compte1.getNumero(); //numero auto incrémenté
		System.out.println("numCompte1="+numCompte1);
		
		Compte compte1Relu = serviceCompte.rechercherCompteParNumero(numCompte1);
		System.out.println("compte1Relu="+compte1Relu);
		Assertions.assertTrue(compte1Relu.getNumero()==numCompte1);
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
