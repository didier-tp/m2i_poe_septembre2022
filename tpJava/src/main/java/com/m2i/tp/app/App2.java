package com.m2i.tp.app;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
		testCollectionModerne();
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
