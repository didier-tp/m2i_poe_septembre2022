package com.m2i.tp;

public class PorteCoulissante extends Porte {
    private int translationGlissiere=0; //0 si ferm�e , 80 si ouvert (cm) 
    
    private void glisser(int translation) {
    	this.translationGlissiere = translation;
    	System.out.println("porte coulissante avec translation:" + this.translationGlissiere);
    	if(translation==80) System.out.println("  porte coulissante maintenant ferm�e");
   	    if(translation==0) System.out.println("   porte coulissante maintenant ouverte");
    }

	

	@Override
	public void fermer() {
		//super.fermer();//si pas abstract
		this.glisser(0);//0cm
	}



	@Override
	public void ouvrir() {
		//super.ouvrir();//si pas abstract
		this.glisser(80);//80cm
	}
    
    
}
