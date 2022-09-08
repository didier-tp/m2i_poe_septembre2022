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
		p1.prenom="jean";
		p1.nom="Bon";
		p1.age=30;
		
		p1.incrementerAge();
		p1.afficher();
		System.out.println(p1.toString());
		System.out.println("p1="+p1.toString());
		System.out.println("p1="+p1);//avec appel automatique/implicite à .toString()
	}

}
