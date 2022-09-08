package com.m2i.tp;

import java.util.Objects;

public class Personne {
	
	private String prenom;
	private String nom;
	private int age;
	
	
	public Personne() {
		super();
	}

	public Personne(String prenom, String nom, int age) {
		super();
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
		return "Personne [prenom=" + prenom + ", nom=" + nom + ", age=" + age + "]";
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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		//this.age = age;
		if(age<0) {
			System.err.println("age negative invalide");
			//throw new RuntimeException("age negative invalide");
		}
		else {
			this.age = age;
		}
	}

    
	
}
