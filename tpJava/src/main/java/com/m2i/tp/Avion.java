package com.m2i.tp;

public class Avion extends ObjetVolant {
	 
	private String nom; //ex : A320_ex1
	public static final int NB_PLACES=200;
	//public static final int NB_PLACES=saisirConstanteNbPlaces();
	private int nbPersonnes=0;
	private Personne[] tabPersonnes ; //de taille = NB_PLACES
	
	private int nbChosesTransportables=0;
	private Transportable tabTransportables[] = new Transportable[300];
	
	public void addTransportable(Transportable t){
		if(nbChosesTransportables==300) return;//arrêter et sortir de la fonction avant de faire n'importe quoi
		tabTransportables[nbChosesTransportables++]=t;
	}
	
	//void afficher() qui affiche tout (et les Personnes en boucle)
		public void afficher() {
			System.out.println("Avion " + nom + " NB_PLACES="+ NB_PLACES );
			System.out.println("\t nbPersonnes=" + nbPersonnes );
			System.out.println("\t personnes montées dans avion:" );
			for(int i=0;i<nbPersonnes;i++) {
				System.out.println("\t\t" + tabPersonnes[i].toString());//polymorphisme sur .toString() de Personne ou Employe 
			}
			//boucler sur le tableau des choses transportables
			//   affichage via .toString()
			//   calculer le poids total des éléments placés dans la soute
			double poidsTotal=0.0;
			System.out.println("\t bagages dans la soute de l'avion:" );
			for(int i=0;i<nbChosesTransportables;i++) {
				System.out.println("\t\t" + tabTransportables[i].toString());//polymorphisme sur .toString() de Personne ou Bagage 
				poidsTotal = poidsTotal + tabTransportables[i].getPoids();//polymorphisme sur .getPoids() de Personne ou Bagage 
			}
			System.out.println("\t poids total des éléments en soute="+poidsTotal);
		}
	
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
