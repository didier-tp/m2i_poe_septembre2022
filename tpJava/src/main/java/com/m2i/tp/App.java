package com.m2i.tp;

public class App {

	public static void main(String[] args) {
		System.out.println("hello");
        m1();
        //m2();
	}
	
	public static void m1() {
		Personne p1=null;
		p1=new Personne();
		System.out.println("p1="+p1);
		/*
		//plus possible si private
		p1.prenom="jean";
		p1.nom="Bon";
		p1.age=30;
		*/
		p1.setPrenom("jean");
		p1.setNom("Bon");
		p1.setAge(30);
		p1.setAge(-5); //demande idiote qui ne sera pas prise en compte
		
		p1.incrementerAge();
		System.out.println("nouvel age de p1 = " + p1.getAge());
		
		p1.afficher();
		System.out.println(p1.toString());
		System.out.println("p1="+p1.toString());
		System.out.println("p1="+p1);//avec appel automatique/implicite à .toString()
		
		Personne p2 = new Personne("jean","Bon",31);
		
		
		if(p1.equals(p2)) {
			System.out.println("p1 et p2 ont mêmes valeurs internes");
		}
		else {
			System.out.println("p1 et p2 ont des valeurs internes un peu différentes");
		}
		
	}

}
