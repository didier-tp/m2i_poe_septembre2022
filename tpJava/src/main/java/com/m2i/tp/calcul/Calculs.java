package com.m2i.tp.calcul;

public class Calculs {

	public static int division2(int a, int b) throws Exception {
		if(b==0) 
			throw new Exception("division par 0 interdite");
		return a/b;
	}
	
	//ArithmeticException est une classe prédéfinie de java
	//qui herite de RuntimeException
	public static int division(int a, int b) throws ArithmeticException {
		if(b==0) 
			throw new ArithmeticException("division par 0 interdite");
		return a/b;
	}
	
	public static double racineCarre(double x) throws CalculException  {
		if(x<0) 
			throw new CalculException("racine carree de x negatif impossible sur double");
		//NB: thrown un peu comme return : si appelé , on sort de la fonction
		return Math.sqrt(x);
	}
	
	public static double racineCarreAvecConversion(String sx) {
		double x =Double.parseDouble(sx);//si sx="16" alors x=16.0 
		//mais si sx="a6' alors NumberFormatException
		return racineCarre(x);
	}

}
