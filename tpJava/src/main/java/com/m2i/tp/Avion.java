package com.m2i.tp;

public class Avion extends ObjetVolant {
	 
	private String nom; //ex : A320_ex1
	public static final int NB_PLACES=200;
	//public static final int NB_PLACES=saisirConstanteNbPlaces();
	private int nbPersonnes=0;
	private Personne[] tabPersonnes ; //de taille = NB_PLACES
	/*
	public static int saisirConstanteNbPlaces() {
		System.out.print("NB_PLACES=");
		return (new java.util.Scanner(System.in)).nextInt();
	}*/
	
	@Override
	public void decrire() {
		//System.out.println("Avion de nom=" + nom + " et avec altitudeMax=" + this.getAltitudeMax()); //avec altitudeMax codé en private
		System.out.println("Avion de nom=" + nom + " et avec altitudeMax=" + this.altitudeMax);
	}
	
	
	
	public Avion(String nom) {
		this.nom=nom;
		tabPersonnes=new Personne[NB_PLACES];
		this.setAltitudeMax(12000);
	}
	

	public Avion() {
		this("nomAvionParDefaut");
	}
	
	public void addPers(Personne p){
		if(nbPersonnes<NB_PLACES) {
			//placer p dans tabPersonnes 
			tabPersonnes[nbPersonnes]=p;
			//et incrementer nbPersonnes
			nbPersonnes++;
		}else {
			System.err.println("Avion dejà plein, plus de place");
		}
	}
	
	//void afficher() qui affiche tout (et les Personnes en boucle)
	public void afficher() {
		System.out.println("Avion " + nom + " NB_PLACES="+ NB_PLACES );
		System.out.println("\t nbPersonnes=" + nbPersonnes );
		System.out.println("\t personnes montées dans avion:" );
		for(int i=0;i<nbPersonnes;i++) {
			System.out.println("\t\t" + tabPersonnes[i].toString());//polymorphisme sur .toString() de Personne ou Employe 
		}
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getNbPersonnes() {
		return nbPersonnes;
	}

	public void setNbPersonnes(int nbPersonnes) {
		this.nbPersonnes = nbPersonnes;
	}

	public Personne[] getTabPersonnes() {
		return tabPersonnes;
	}


	


}