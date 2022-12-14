package com.m2i.tp.app;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.m2i.tp.Avion;
import com.m2i.tp.Bagage;
import com.m2i.tp.Employe;
import com.m2i.tp.ObjetVolant;
import com.m2i.tp.Personne;
import com.m2i.tp.Porte;
import com.m2i.tp.PorteBattant;
import com.m2i.tp.PorteCoulissante;
import com.m2i.tp.Transportable;
import com.m2i.tp.calcul.CalculException;
import com.m2i.tp.calcul.Calculs;




public class App {
	
	private static Logger logger = LoggerFactory.getLogger(App.class);


	public static void main(String[] args) {
		//m0();
        //m1();
        //m2();
        m3(); //avion
        //testPorte();
        m4();
        //java.util.Date d = new java.util.Date();
        Date d = new Date();
        System.out.println("d="+d);
	}
	
	public static void m4() {
		int a1=3; int b1=2;
		
		try {
			//division remonte de execption de type RuntimeException
			//et le try/catch est alors facultatif
			int res1 = Calculs.division(a1, b1);
			System.out.println("res1= "+res1);
			b1=0;
			res1 = Calculs.division(a1, b1);
			System.out.println("res1="+res1);
		} catch (RuntimeException e) {
		   e.printStackTrace();
		   //System.err.println(e.getMessage());
		}
		
		try {
			//division2 remonte une exception n'heritant pas de RuntimeException
			//le try/catch est obligatoire , sinon ça ne compile pas 
			int res2 = Calculs.division2(a1, b1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		double x=9;
		double res3;
		try {
			res3 = Calculs.racineCarre(x);
			System.out.println("pour x="+x + ", racinneCarre = " + res3);
			//x=-9;
			//res3 = Calculs.racineCarre(x);
			System.out.println("pour x="+x + ", racinneCarre = " + res3);
			res3 = Calculs.racineCarreAvecConversion("36");
			System.out.println("pour sx=36, racinneCarre = " + res3);
			//res3 = Calculs.racineCarreAvecConversion("a9");
			res3 = Calculs.racineCarreAvecConversion("-9");
			System.out.println("pour sx=a6, racinneCarre = " + res3);
		} 
		catch (NumberFormatException e) {
			 e.printStackTrace();
			 //System.err.println(e.getMessage());
		}
		catch (RuntimeException e) {
			 //e.printStackTrace();
			 System.err.println(e.getMessage());
		}
		
		System.out.println("suite ...");
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
		Avion.Etats etatAvion = avion1.getEtat();
		System.out.println("etatAvion="+etatAvion);
		System.out.println("etatAvion.ordinal()="+etatAvion.ordinal());
		avion1.setEtat(Avion.Etats.EN_VOL);
		System.out.println("nouvel etat de l'avion=" + avion1.getEtat());
		
		
		avion1.addPers(new Employe("prenomPilote","nomPilote",45,"pilote",5000.0));
		avion1.addPers(new Employe("prenomHotesse","nomHotesse",35,"hotesse",2500.0));
		avion1.addPers(new Personne("prenomPassager1","nomPassager1",25));
		avion1.addPers(new Personne("prenomPassager2","nomPassager3",25));
		
		avion1.addTransportable(new Bagage("sac1",12.5 , 25.5));
		avion1.addTransportable(new Bagage("valise en carton",12.5 , 25.5));
		Transportable passagerClandestin= new Personne("carlos","Ghosn",68);//poids = 75kg par defaut
		avion1.addTransportable(passagerClandestin);
		
		avion1.afficher();
		
		ObjetVolant objV ;
		//objV = new ObjetVolant(); //new direct interdit sur classe abstract
		objV=avion1;
		//objV=new Helicoptere();
		System.out.println("altitudeMax=" + objV.getAltitudeMax());
		objV.decrire();
		
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
	
	public static void testPorte() {
		Porte p=null;
		//p=new Porte(); // new Porte() maintenant interdit si classe Porte abstraite
		PorteCoulissante pc = new PorteCoulissante();
		PorteBattant pb = new PorteBattant();
		p = pc;
		p.ouvrir(); p.fermer(); //Polymorphisme
		p.setCouleur("rouge"); System.out.println(" ... de couleur " + p.getCouleur());
		/*
		 En langage C , if( p.type == PORTE_COULISSANTE)
		          ouvrirPorteCoulissante(p);
		      else if( p.type == PORTE_BATTANT)
		            ouvrirPorteBattant(p);
		 */
		p = pb;
		p.ouvrir(); p.fermer(); //Polymorphisme
		
		//En plus des méthodes polymorphes abstraites ou concrètes ,
		//une classe abstraite peut comporter des attributs et méthodes concrets (ici couleur):
		p.setCouleur("vert");  System.out.println(" ... de couleur " + p.getCouleur());
		
		//------------- polymorphisme en boucle -----
		Porte[] tabRefPortes = new Porte[2];
		tabRefPortes[0]=pc;
		tabRefPortes[1]=pb;
		for(int i=0;i<2;i++) {
			tabRefPortes[i].ouvrir();
		}
		for(int i=0;i<2;i++) {
			tabRefPortes[i].fermer();
		}
	}

}
