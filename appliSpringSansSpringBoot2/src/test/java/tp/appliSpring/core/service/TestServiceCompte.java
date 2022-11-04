package tp.appliSpring.core.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import tp.appliSpring.core.MySpringApplication;
import tp.appliSpring.core.entity.Compte;

public class TestServiceCompte {
	private ServiceCompte serviceCompte; //à tester
	
	@BeforeEach //ou bien @BeforeAll
	public void initialisation() {
		AnnotationConfigApplicationContext springContext = new
			    AnnotationConfigApplicationContext(MySpringApplication.class) ;
			
			this.serviceCompte  = springContext.getBean(ServiceCompte.class);
	}
	
	@Test
	public void testRechercherCompte() {
		Compte compte1 = serviceCompte.rechercherCompteParNumero(1L);
		System.out.println("compte1="+compte1);
		Assertions.assertTrue(compte1.getNumero()==1L);
	}

}
