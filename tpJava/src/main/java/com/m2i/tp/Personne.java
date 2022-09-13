package com.m2i.tp;

import java.util.Objects;
/**
 * Personne = Personne humaine
 * 
 * @author Didier Defrance
 *
 */
public class Personne implements Transportable {
	
	private static int esperanceVie=72; //moyenne mondiale (selon statistique)
	private Integer numero; // 1,2,3,... (identifiant unique, clef primaire)
	private String prenom;
	private double poids = 75.0; //poids moyen en tant que valeur par défaut
	private String nom;
	//private int age;  // -5 , 0 , 5 mais pas null
	private Integer age;// -5 , 0 , 5 ou null 
	                    //null signifie inconnu dans colonne d'une table d'une base de données
	                    //null signifie "pas saisie" dans un formulaire
	
	
	@Override
	public double getPoids() {
		//return 75.0; //poids moyen (V1)
		return this.poids; //V2
	}
	
	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public void setPoids(double poids) {
		this.poids = poids;
	}


	@Override
	public double getVolume() {
		return 75.0; //volume moyen (V1)
	}
	
	public Personne() {
		super();
	}

	public Personne(String prenom, String nom, int age) {
		super();
		this.prenom = prenom;
		this.nom = nom;
		this.age = age;
	}
	

	public Personne(Integer numero, String prenom, String nom, Integer age) {
		super();
		this.numero = numero;
		this.prenom = prenom;
		this.nom = nom;
		this.age = age;
	}

	public void afficher() {
		System.out.println("Je suis une personne, prenom=" + prenom + " nom=" +nom + " age=" + age );
	}
	
	public void incrementerAge() {
		this.age = this.age + 1;
		//age = age+1;
		//age++;
	}



	@Override
	public String toString() {
		return "Personne [numero=" + numero + ", prenom=" + prenom + ", poids=" + poids + ", nom=" + nom + ", age="
				+ age + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(age, nom, prenom);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Personne other = (Personne) obj;
		return age == other.age && Objects.equals(nom, other.nom) && Objects.equals(prenom, other.prenom);
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		//this.age = age;
		if(age<0) {
			System.err.println("age negative invalide");
			//throw new RuntimeException("age negative invalide");
		}
		else {
			this.age = age;
		}
	}

	public static int getEsperanceVie() {
		return esperanceVie;
	}

	public static void setEsperanceVie(int esperanceVie) {
		Personne.esperanceVie = esperanceVie;
	}

	
}
