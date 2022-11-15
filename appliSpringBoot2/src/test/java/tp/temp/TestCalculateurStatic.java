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

}
