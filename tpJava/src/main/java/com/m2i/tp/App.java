package com.m2i.tp;

public class App {

	public static void main(String[] args) {
		//m0();
        m1();
        //m2();
        m3(); //avion
        
	}
	
	public static void m0() {
		System.out.println("hello");
		Integer iObj = new Integer(10); //ou bien plus simplement Integer iObj = 10; depuis java 5
		//iObj.setIntValue(11); impossible sur classe Integer car immuable
		//seule possibilité : 
		iObj = new Integer(iObj+1); //ou bien iObj = iObj+1
		System.out.println("iObj="+iObj);
		String ch="abc";
        ch=ch + "_suite";
	}
	
	public static void m3() {
		Avion avion1 = new Avion("A320_ex1");
		avion1.addPers(new Employe("prenomPilote","nomPilote",45,"pilote",5000.0));
		avion1.addPers(new Employe("prenomHotesse","nomHotesse",35,"hotesse",2500.0));
		avion1.addPers(new Personne("prenomPassager1","nomPassager1",25));
		avion1.addPers(new Personne("prenomPassager2","nomPassager3",25));
		
		avion1.afficher();
		//calculer et afficher la racine carrée de 9 via la classe prédéfinie Math
		System.out.println("la racine carree de 9 vaut "  + Math.sqrt(9.0));
		
		System.out.println("espérance de vie mondiale avant covid="  + Personne.getEsperanceVie());
		Personne.setEsperanceVie(Personne.getEsperanceVie()-1);
		System.out.println("espérance de vie mondiale durant covid="  + Personne.getEsperanceVie());
		Personne.setEsperanceVie(Personne.getEsperanceVie()+1);
		System.out.println("espérance de vie mondiale apres vaccins pour covid="  + Personne.getEsperanceVie());
	}
	
	public static void m2() {
		String jourDeLaSemaine = "lundi;mardi;mercredi;jeudi;vendredi;samedi;dimanche";
		int posPremierPointvirgule = jourDeLaSemaine.indexOf(";"); //retourne -1 si aucun ;
		System.out.println("posPremierPointvirgule="+posPremierPointvirgule);
		String premierJour = jourDeLaSemaine.substring(0,posPremierPointvirgule);
		System.out.println("premierJour="+premierJour);
		
		String[] tabJours = jourDeLaSemaine.split(";");
		System.out.println("deuxiemeJour=" + tabJours[1]); //1=2-1 car indices à partir de 0
		
		//construire une chaine avec les valeurs "1_2_..._64"
		//StringBuilder buffer = new StringBuilder(); //possible 
		StringBuilder buffer = new StringBuilder(64*3);//largement assez le place pour 64fois _32 ou _33 ou ...
		for(int i=0;i<64;i++) {
			if(i>0) {
				buffer.append('_');
			}
			buffer.append(i+1);
		}
		String s=buffer.toString();
		System.out.println("s="+s);
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
		
		Employe e1 = new Employe();
		e1.afficher();
		Employe e2 = new Employe("axelle","Aire",30,"directrice",4500.0);
		e2.incrementerAge();
		e2.afficher();
		System.out.println("e2="+e2.toString()); //ou bien System.out.println("e2="+e2);
		
		Personne p=null; 
		p=p2;
		p.afficher(); //affichera prenom,nom,age
		p=e2;
		p.afficher(); //affichera prenom,nom,age,fonction,salaire
		if(p instanceof Employe) {
			String f =((Employe)p).getFonction();
			System.out.println("fontion="+f);
		}
	}

}
