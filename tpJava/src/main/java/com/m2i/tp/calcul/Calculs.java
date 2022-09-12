package com.m2i.tp.calcul;

public class Calculs {

	public static int division2(int a, int b) throws Exception {
		if(b==0) 
			throw new Exception("division par 0 interdite");
		return a/b;
	}
	
	
	public static int division(int a, int b) throws RuntimeException {
		if(b==0) 
			throw new RuntimeException("division par 0 interdite");
		return a/b;
	}
	
	public static double racineCarre(double x) throws RuntimeException  {
		if(x<0) 
			throw new RuntimeException("racine carree de x negatif impossible sur double");
		//NB: thrown un peu comme return : si appelé , on sort de la fonction
		return Math.sqrt(x);
	}

}
