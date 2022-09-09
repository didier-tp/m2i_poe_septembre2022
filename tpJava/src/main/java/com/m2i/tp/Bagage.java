package com.m2i.tp;

public class Bagage implements Transportable{
	
	private String nom; //ex: sac1 , valise1 , ...
	private double poids;
	private double volume;
	

	public Bagage() {
	}
	
	
	public Bagage(String nom, double poids, double volume) {
		super();
		this.nom = nom;
		this.poids = poids;
		this.volume = volume;
	}
	
	
	@Override
	public String toString() {
		return "Bagage [nom=" + nom + ", poids=" + poids + ", volume=" + volume + "]";
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public double getPoids() {
		return poids;
	}


	public void setPoids(double poids) {
		this.poids = poids;
	}


	public double getVolume() {
		return volume;
	}


	public void setVolume(double volume) {
		this.volume = volume;
	}
	
	

}
