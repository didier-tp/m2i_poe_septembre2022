package com.m2i.tp;

public abstract class ObjetVolant {
	
	protected int altitudeMax = 500; //par defaut
	
	public abstract void decrire();

	public ObjetVolant() {
	}

	public int getAltitudeMax() {
		return altitudeMax;
	}

	public void setAltitudeMax(int altitudeMax) {
		this.altitudeMax = altitudeMax;
	}
	

}
