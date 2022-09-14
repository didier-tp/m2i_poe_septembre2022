package com.m2i.tp.app;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import com.m2i.tp.Personne;
import com.m2i.tp.dao.DaoPersonne;
import com.m2i.tp.dao.DaoPersonneSimu;
/*
//remplacé par classe imbriquée ou bien lambda expression:
final class ComparateurPersonneSelonAge implements Comparator<Personne>{
	@Override
	public int compare(Personne o1, Personne o2) {
		return o1.getAge() - o2.getAge();
	}
}
*/
final class ComparateurPersonneSelonNomPrenom implements Comparator<Personne>{
	@Override
	public int compare(Personne o1, Personne o2) {
		if(o1.getNom()==null || o2.getNom()==null) return 0;
		int resComparaisonNom =  o1.getNom().compareTo(o2.getNom());
		if(resComparaisonNom!=0) {
			return resComparaisonNom;
		}
		else {
			return  o1.getPrenom().compareToIgnoreCase(o2.getPrenom());
		}
	}
}

public class App2 {

	public static void main(String[] args) {
		String typeAff = System.getProperty("aff"); //pour récupérer la valeur choisie
		//au démarrage -Daff=majuscule ou -Daff=minuscule ou ...
		String username = "Didier Devloppeur Fou";
		if(typeAff!=null && typeAff.equals("majuscule")) {
			username =username.toUpperCase();
		}
		System.out.println("username="+username);
		System.out.println("os.name="+System.getProperty("os.name"));
		//testAncienneCollection();
		//testCollectionModerne();
		testManipCollection();
		//testDaoSimu();
	}
	
	public static void testDaoSimu() {
	    DaoPersonne daoPersonne  = new DaoPersonneSimu();
	    daoPersonne.addPersonne(new Personne(null,"jean","Bon",31));
	    daoPersonne.addPersonne(new Personne(null,"alex","Therieur",25));
	    daoPersonne.addPersonne(new Personne(null,"dupond","Durand",45));
	    daoPersonne.addPersonne(new Personne(null,"alain","Therieur",23));
	    
	    Personne p1 = daoPersonne.findPersonneByNumero(1);
	    System.out.println("p1="+p1); //p1.toString()
	    p1.incrementerAge();
	    daoPersonne.updatePersonne(p1);
	    Personne p1MisAJour = daoPersonne.findPersonneByNumero(1);
	    System.out.println("p1MisAJour="+p1MisAJour);
	    
	    daoPersonne.deletePersonne(1);
	    
	    List<Personne> listePers = daoPersonne.findAllPersonnes();
	    System.out.println("listePers="+listePers);
	    
	}
	
	public static void testManipCollection() {
		List<Personne> listePersonnes = new ArrayList<>();
		listePersonnes.add(new Personne("jean","Bon",31));
		listePersonnes.add(new Personne("alex","Therieur",25));
		listePersonnes.add(new Personne("dupond","Durand",45));
		listePersonnes.add(new Personne("alain","Therieur",23));
		listePersonnes.add(new Personne("sophie","Zorro",53));
		
		//etape 1 (effectuer un filtrage)
		//on va creer une autre collection
		//qui va comporter que les personnes qui au 40ans au plus:
		List<Personne> listePersonnes40ansAuplus = new ArrayList<>();
		for(Personne p : listePersonnes) {
			if(p.getAge()<=40) {
				listePersonnes40ansAuplus.add(p);
			}
		}
		
		//etape2: trier les personnes selon l'age ou bien l'ordre alphabetique sur nom (puis prenom):
		//Comparator<Personne> comparateurPersonneSelonAge = new ComparateurPersonneSelonAge();
		Comparator<Personne> comparateurPersonneSelonNomPrenom = new ComparateurPersonneSelonNomPrenom();
		//Collections.sort(listePersonnes40ansAuplus,comparateurPersonneSelonAge);
		//Collections.sort(listePersonnes40ansAuplus,comparateurPersonneSelonNomPrenom);
		Collections.sort(listePersonnes40ansAuplus,
				new /* classe imbriquée anonyme qui implements */ Comparator<Personne>() {
					@Override
					public int compare(Personne o1, Personne o2) {
						return o1.getAge() - o2.getAge();
					}
				}
				);
		
		//affiche listePersonnes40ansAuplus via le for() au sens forEach
		int total_age=0; 
		System.out.println("listePersonnes40ansAuplus:");
		for(Personne p : listePersonnes40ansAuplus) {
			System.out.println("\t" + p); //p.toString() implicitement déclenché
			total_age= total_age + p.getAge();
		}
		double ageMoyen = total_age / listePersonnes40ansAuplus.size();
		System.out.println("age moyen des personnes de 40ans au plus:" + ageMoyen);
	}
	
	public static void testCollectionModerne() {
		//à partir de java 5 (fin 2004)
		List<String> listeObjetsJava = new ArrayList<String>();
		listeObjetsJava.add("abc");
		listeObjetsJava.add("def"); //.add(8) interdit
		System.out.println("nbElements="+listeObjetsJava.size());
		
		/*
		Iterator<String> it = listeObjetsJava.iterator();
		while(it.hasNext()) {
			String s = it.next();
			System.out.println("s="+s);
		}*/
		
		//boucle for au sens forEach 
		for(String s : listeObjetsJava) {
			System.out.println("s="+s);
		}
	}
	
	public static void testAncienneCollection() {
		//code des années 1999-2004 (avant java 5)
		List listeObjetsJava = new ArrayList();
		listeObjetsJava.add("abc");
		listeObjetsJava.add("def");
		System.out.println("nbElements="+listeObjetsJava.size());
		
		Iterator it = listeObjetsJava.iterator();
		while(it.hasNext()) {
			String s = (String) it.next();
			System.out.println("s="+s);
		}
		
	}

}
