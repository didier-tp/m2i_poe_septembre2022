package com.m2i.tp;

//Employe herite de Personne
public class Employe extends Personne {
	private String fonction; //ex: "Developpeur" , "Commercial"
	private Double salaire; //salaire mensuel 
	
	
	//constructeurs :
	public Employe() {
		super(); //pour un personne ordinaire l'essence c'est super (surtout au prix d'aujourd'hui)
	}
	
	
	public Employe(String prenom, String nom, int age ,String fonction, Double salaire) {
		super(prenom,nom,age);
		this.fonction = fonction;
		this.salaire = salaire;
	}


	//redefinir afficher() et toString()
	public void afficher() {
		super.afficher();
		System.out.println("Employe avec fonction="+fonction + " et salaire="+salaire);
	}
	

	public String toString() {
		return "Employe avec fonction="+fonction 
				+" et salaire="+salaire 
				+ " heritant de " + super.toString();
	}


	
	
	//+get/set
	public String getFonction() {
		return fonction;
	}
	

	public void setFonction(String fonction) {
		this.fonction = fonction;
	}
	public Double getSalaire() {
		return salaire;
	}
	public void setSalaire(Double salaire) {
		this.salaire = salaire;
	}
}
