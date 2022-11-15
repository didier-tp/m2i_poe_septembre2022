package tp.temp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestCalculateur {
	
	private Calculateur calculateur; //à tester
	
	public TestCalculateur() {
		System.out.println("constructeur de TestCalculateur appelé , this="+this);
	}
	
	@BeforeEach
	public void initCalculateur() {
		System.out.println("initCalculateur() préfixée par @BeforeEach appelée , this="+this);
		calculateur=new Calculateur();
	}
	
	@Test
	public void testSomme() {
		System.out.println("testSomme() appelée , this="+this);
		calculateur.addVal(8);
		calculateur.addVal(2);
		double s  = calculateur.getSomme();
		Assertions.assertEquals(10.0, s , 0.0000001);
	}
	
	@Test
	public void testN() {
		System.out.println("testN() appelée , this="+this);
		calculateur.addVal(7);
		calculateur.addVal(5);
		int n  = calculateur.getN();
		Assertions.assertTrue(n==2);
	}

}
