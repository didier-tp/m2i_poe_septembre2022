package com.m2i.tp;

public class Personne {
	
	public String prenom;
	public String nom;
	public int age;
	
	public void afficher() {
		System.out.println("Je suis une personne, prenom=" + prenom + " nom=" +nom + " age=" + age );
	}
	
	public void incrementerAge() {
		this.age = this.age + 1;
		//age = age+1;
		//age++;
	}

}
