package com.m2i.tp;

public interface Transportable {
     double getPoids(); //en kg
	 double getVolume(); //en litre
}

/*
NB: cette interface sera implementée par:
     Personne (code serieux avec private et get/set
               ou bien getPoids() { return 70.0; } )
     Bagage (nouvelle classe)
     
et dans la classe Avion , il y aura une soute 
avec un tableau de choses transportable (bagages , ....)     
*/