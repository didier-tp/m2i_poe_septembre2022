package com.m2i.tp.app;

import java.util.List;

import com.m2i.tp.Personne;
import com.m2i.tp.dao.DaoPersonneJdbc;

public class App6 {

	public static void main(String[] args) {
		DaoPersonneJdbc daoPersonne  = new DaoPersonneJdbc();
        //Connection cn = daoPersonne.etablirConnection();
        //System.out.println("connection jdbc = " + cn);
		daoPersonne.addPersonne(new Personne(99,"prenom99", "nom99" , 99));
		List<Personne> listePers = daoPersonne.findAllPersonnes();
		listePers.stream().forEach(p -> System.out.println(p));
	}

}
