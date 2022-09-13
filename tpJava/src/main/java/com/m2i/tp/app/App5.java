package com.m2i.tp.app;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class App5 {

	public static void main(String[] args) {
		Random random = new Random();
        double valeurAleatoire = random.nextDouble(); //entre 0.0 et 1.0
        int nombreAleatoire = (int) (valeurAleatoire * 100);//entre 0 et 100
        System.out.println("nombreAleatoire="+nombreAleatoire);
        
        //classes Date , Calendar , SimpleDateFormat qui existent depuis très longtemps (début 2000)
        
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
        
        SimpleDateFormat simpleDateFormatAvecHeure = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String sdateAvecHeure = simpleDateFormatAvecHeure.format(dateAujourdhui);
        System.out.println("dateAujourdhui avec heure = " +sdateAvecHeure);
        
        Calendar cal = Calendar.getInstance(); 
        cal.set(2022, 12-1, 25); //noel 2022 (25/12/2022) 
        Date dateNoel2022 = cal.getTime();
        System.out.println("noel2022 en francais = " + simpleDateFormatFr.format(dateNoel2022));
        
        // avec classes nouvelles de java 8 (java.time.LocalDateTime) 
        LocalDateTime nowDateTime = LocalDateTime.now();
        System.out.println("nowDateTime="+nowDateTime);
        
	}

}
