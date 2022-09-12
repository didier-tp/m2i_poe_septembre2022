package com.m2i.tp.calcul;

public class Calculs {

	
	public static int division(int a, int b) throws RuntimeException {
		if(b==0) 
			throw new RuntimeException("division par 0 interdite");
		return a/b;
	}
	
	public static double racineCarre(double x) {
		//si x<0  racine carree de x negatif impossible sur double
		return Math.sqrt(x);
	}

}
