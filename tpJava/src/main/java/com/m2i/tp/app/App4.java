package com.m2i.tp.app;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.m2i.tp.Personne;

public class App4 {

	public static void main(String[] args) {
		testFichiers();

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
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
