package com.m2i.tp.app;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

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
		//testManipCollection();
		testStreamJava8();
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
	
	public static void testStreamJava8() {
		List<Personne> listePersonnes = new ArrayList<>();
		listePersonnes.add(new Personne("jean","Bon",31));
		listePersonnes.add(new Personne("alex","Therieur",25));
		listePersonnes.add(new Personne("dupond","Durand",45));
		listePersonnes.add(new Personne("alain","Therieur",23));
		listePersonnes.add(new Personne("sophie","Zorro",53));
		
		//a faire en Tp (appliquer exemple page 93)
		List<Personne> listePersonnesFiltreesTrieesEtTransformees =
				listePersonnes.stream()
				.filter(p -> p.getAge() <= 40)
				.sorted((p1,p2)-> p1.getAge()-p2.getAge())
				//.map(p -> { p.setNom(p.getNom().toUpperCase()); return p; }) //defaut : ça modifie les personnes de la liste d'origine
				.map (p -> new Personne(p.getNumero(),p.getPrenom(),p.getNom().toUpperCase(),p.getAge()))
				.collect(Collectors.toList());
		
		System.out.println("listePersonnesFiltreesTrieesEtTransformees=");
		for(Personne p : listePersonnesFiltreesTrieesEtTransformees) {
			System.out.println("\t" + p);
		}
		
		System.out.println("listePersonnes="+listePersonnes);
		
		//operation terminale = affichage en boucle via .forEach() et println()
		listePersonnes.stream()
				.filter(p -> p.getAge() <= 40)
				.sorted((p1,p2)-> p1.getAge()-p2.getAge())
				.forEach(p -> System.out.println("p="+p));
		
		int sommeAge = listePersonnes.stream()
		        .map(p -> p.getAge())
		        .reduce(0 , (x,y)->x+y); //.reduce est une operation terminale (d'un Stream de Integer on reduit ça en un seul Integer)
		System.out.println("moyenne des ages = " + sommeAge/listePersonnes.size());
		
		Double moyenneAge = listePersonnes.stream()
        .mapToInt(p -> p.getAge())
        .average().orElse(Double.NaN);
		System.out.println("moyenneAge="+moyenneAge);
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
		
		
		Collections.sort(listePersonnes40ansAuplus,
				/*(Personne o1, Personne o2) -> {	return o1.getAge() - o2.getAge();	}*/
				/*(Personne o1, Personne o2) ->  o1.getAge() - o2.getAge() */
				(p1, p2) ->  p1.getAge() - p2.getAge()
				);
		//si autre tri , le prochain tri  annule/remplace le resultat du tri précedent
		
		//variante du tri par ordre décroissant des ages:
		Collections.sort(listePersonnes40ansAuplus,(p1, p2) ->  p2.getAge() - p1.getAge());
		
		//variante du tri par ordre croissant sur les prénoms:
		Collections.sort(listePersonnes40ansAuplus,(p1, p2) ->  p1.getPrenom().compareTo(p2.getPrenom()));
		
		//variante du tri via reférence sur une fonction existante qui compare selon ordre croissant sur les ages:
		Collections.sort(listePersonnes40ansAuplus, Personne::comparerSelonAge);
		
		
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
	
	public static void testManipCollectionAvantJava8() {
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
