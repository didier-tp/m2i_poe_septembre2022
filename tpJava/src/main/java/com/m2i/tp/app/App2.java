package com.m2i.tp.app;

public class App2 {

	public static void main(String[] args) {
		String typeAff = System.getProperty("aff"); //pour récupérer la valeur choisie
		//au démarrage -Daff=majuscule ou -Daff=minuscule ou ...
		String username = "Didier Devloppeur Fou";
		if(typeAff!=null && typeAff.equals("majuscule")) {
			username =username.toUpperCase();
		}
		System.out.println("username="+username);
	}

}
