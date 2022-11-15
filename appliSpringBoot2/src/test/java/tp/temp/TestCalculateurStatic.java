package tp.temp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class TestCalculateurStatic {
	
	private static Calculateur calculateur;
	
	@BeforeAll
	public static void initCalculateur() {
		calculateur = new Calculateur();
	}
	
	@Test
	public void testCarre() {
		Assertions.assertEquals(16, calculateur.carre(4) , 0.00000001);
	}
	
	@Test
	public void testCarre2() {
		Assertions.assertEquals(9, calculateur.carre(3) , 0.00000001);
	}

}
