package com.m2i.tp.app;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.m2i.tp.Personne;

public class App4 {

	public static void main(String[] args) {
		testFichiers();
		testFichierProperties();
	}

	public static void testFichierProperties() {
		//getClass() possible dans une méthode qui n'est pas static
		//si méthode static , App4.class
		InputStream fluxLecture = App4.class.getClassLoader().getResourceAsStream("config.properties");
		System.out.println("fluxLecture="+fluxLecture);
		Scanner reader = new Scanner(fluxLecture);
		String ligne;
		while(reader.hasNextLine()){
			ligne=reader.next();
			System.out.println("ligne="+ligne);
		}
		reader.close();
	}
	
	public static void testFichiers() {
		try {
			FileInputStream fluxLecture = new FileInputStream("personnes.csv");
			BufferedReader fluxLectureAvecBuffer =
					new BufferedReader(new InputStreamReader(fluxLecture));
			
			String ligne;
			int compteur=0;
			List<Personne> listePersonnes = new ArrayList<>();
			do {
				compteur++;
				ligne=fluxLectureAvecBuffer.readLine();
				//System.out.println("ligne="+ligne);
				if(compteur>1 && ligne != null) {
					//ligne de données (pas la première avec les significations des colonnes)
					String[] parties  = ligne.split(";");
					listePersonnes.add(new Personne(
							Integer.parseInt(parties[0]),
							parties[1],
							parties[2],
							Integer.parseInt(parties[3])
							));
				}
			}while(ligne!=null);
			fluxLectureAvecBuffer.close();
			System.out.println("listePersonnes="+listePersonnes);
			
			//on modifie listePersonnes en enlevant les objets "Personne" qui ont plus de 30 ans
			/*
			for(Personne p : listePersonnes) {
				if(p.getAge()>30)
					listePersonnes.remove(p);
			}
			//java.util.ConcurrentModificationException
		    */
			for(int i=listePersonnes.size()-1; i>=0 ; i--) {
				Personne p = listePersonnes.get(i);
				if(p.getAge()>30)
					listePersonnes.remove(i);
			}
			System.out.println("listePersonnes apres modification="+listePersonnes);
			//on génère un fichier "personnes2.csv" avec le contenu de listePersonnes.
			FileOutputStream of = new FileOutputStream("personnes2.csv");
			//PrintStream ps = new PrintStream(of);
			PrintWriter pw = new PrintWriter(of); //NB: PrintWriter est une variante de PrintStream un peu plus recente
			pw.println("numero;prenom;nom;age");
			for(Personne p : listePersonnes) {
				pw.printf("%d;%s;%s;%d\n", p.getNumero(), p.getPrenom() , p.getNom() , p.getAge());
			}
			pw.close();
			//NB: sous eclipse , pour voir le fichier généré : click droit "Refresh" sur projet tpJava
			//                   puis click droit / open with ... / text editor sur fichier .csv
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
