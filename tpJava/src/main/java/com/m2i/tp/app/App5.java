package com.m2i.tp.app;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class App5 {

	public static void main(String[] args) {
		Random random = new Random();
        double valeurAleatoire = random.nextDouble(); //entre 0.0 et 1.0
        int nombreAleatoire = (int) (valeurAleatoire * 100);//entre 0 et 100
        System.out.println("nombreAleatoire="+nombreAleatoire);
        
        
        Date dateAujourdhui = new Date();
        System.out.println("dateAujourdhui="+dateAujourdhui);
        long nbMsEcouleesDepuis01_01_1970 = dateAujourdhui.getTime();
        System.out.println("nbMsEcouleesDepuis01_01_1970="+nbMsEcouleesDepuis01_01_1970);
        
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String sdate = simpleDateFormat.format(dateAujourdhui);
        System.out.println("dateAujourdhui au format international = sdate = " +sdate);
        
        SimpleDateFormat simpleDateFormatFr = new SimpleDateFormat("dd/MM/yyyy");
        String sdateFr = simpleDateFormatFr.format(dateAujourdhui);
        System.out.println("dateAujourdhui au format francais = sdateFr = " +sdateFr);
        
	}

}
