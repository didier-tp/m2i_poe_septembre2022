package com.m2i.tp.calcul;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Calculs {
	
	private static Logger logger = LoggerFactory.getLogger(Calculs.class);

	
	//public static double racineCarreAvecConversion(String sx) throws CalculException,NumberFormatException{
		public static double racineCarreAvecConversion(String sx) throws RuntimeException,NumberFormatException{
			double x =Double.parseDouble(sx);//si sx="16" alors x=16.0 
			//mais si sx="a6' alors NumberFormatException
			double res = 0;
			try {
				res=racineCarre(x);
			} catch (CalculException e) {
				//e.printStackTrace();
				logger.error("echec racineCarreAvecConversion avec x="+x ,e);
				throw new RuntimeException("echec racineCarreAvecConversion",e);
			}
			return res;
		}

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
		//logger.debug("dans racineCarre x="+x);
		return Math.sqrt(x);
	}
	

}
