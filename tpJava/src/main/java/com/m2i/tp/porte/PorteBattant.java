package com.m2i.tp.porte;

public class PorteBattant extends Porte {
     private int angleRotation=0; //0 ferm�e , 90 degr� = ouvert
     
     private void pivoter(int angle) {
    	 this.angleRotation=angle;
    	 System.out.println("porte a 1 battant, angle rotation:"+this.angleRotation);
    	 if(angle==90) System.out.println("  porte a 1 battant maintenant ferm�e");
    	 if(angle==0) System.out.println("   porte a 1 battant maintenant ouverte");
     }

	@Override
	public void ouvrir() {
		//super.ouvrir();
		this.pivoter(90);//90 degr�s
	}

	@Override
	public void fermer() {
		//super.fermer();
		this.pivoter(0);//0 degr�
	}
     
     
}
