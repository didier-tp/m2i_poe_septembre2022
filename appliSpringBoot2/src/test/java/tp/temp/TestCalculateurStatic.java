package tp.temp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

//@TestMethodOrder(OrderAnnotation.class)
public class TestCalculateurStatic {
	
	private static Calculateur calculateur;
	
	@BeforeAll
	public static void initCalculateur() {
		calculateur = new Calculateur();
	}
	
	@Test 
	//@Order(2)
	public void testCarre() {
		System.out.println("testCarre");
		Assertions.assertEquals(16, calculateur.carre(4) , 0.00000001);
	}
	
	@Test
	//@Order(1)
	public void testCarreBis() {
		System.out.println("testCarreBis");
		Assertions.assertEquals(9, calculateur.carre(3) , 0.00000001);
	}
	
	@Test 
	public void testRacineCarree() {
		System.out.println("testRacineCarree");
		Assertions.assertEquals(5, calculateur.racineCarree(25) , 0.00000001);
	}
	/*
	//PAS BIEN : le comportement de .getMoyenne() dépend de l'état du calculateur qui doit être
	//réinitilisé et donc pas static/partagé
	@Test
	public void testMoyenne() {
		System.out.println("testMoyenne() appelée , this="+this);
		calculateur.addVal(8);
		calculateur.addVal(6);
		double m  = calculateur.getMoyenne();
		Assertions.assertEquals(7, m , 0.0000001);
	}
	
	//PAS BIEN : le comportement de .testSomme() dépend de l'état du calculateur qui doit être
	//réinitilisé et donc pas static/partagé
	@Test
	public void testSomme() {
		System.out.println("testSomme() appelée , this="+this);
		calculateur.addVal(8);
		calculateur.addVal(2);
		double s  = calculateur.getSomme();
		Assertions.assertEquals(10.0, s , 0.0000001);
	}
    */
}
