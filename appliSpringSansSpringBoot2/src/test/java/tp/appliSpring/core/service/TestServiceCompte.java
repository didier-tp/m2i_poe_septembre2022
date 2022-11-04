package tp.appliSpring.core.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import tp.appliSpring.core.MySpringApplication;
import tp.appliSpring.core.entity.Compte;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes={MySpringApplication.class}) //java config
public class TestServiceCompte {
	
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
	public void testRechercherCompte() {
		Compte compte1 = new Compte(null,"CompteXy",200.0);
		serviceCompte.sauvegarderCompte(compte1);
		Long numCompte1 = compte1.getNumero(); //numero auto incrémenté
		System.out.println("numCompte1="+numCompte1);
		
		Compte compte1Relu = serviceCompte.rechercherCompteParNumero(numCompte1);
		System.out.println("compte1Relu="+compte1Relu);
		Assertions.assertTrue(compte1Relu.getNumero()==numCompte1);
	}

}
