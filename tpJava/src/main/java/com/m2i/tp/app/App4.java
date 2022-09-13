package com.m2i.tp.app;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

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
			do {
				ligne=fluxLectureAvecBuffer.readLine();
				System.out.println("ligne="+ligne);
			}while(ligne!=null);
			fluxLectureAvecBuffer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
