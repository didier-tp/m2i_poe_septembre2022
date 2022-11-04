package tp.appliSpring.exemple;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import tp.traducteur.Traducteur;
import tp.traducteur.TraducteurFrancaisAnglais;

public class ExempleApp {

	public static void main(String[] args) {
		avecSpring();
		//sansSpring();
	}
	public static void sansSpring() {
		MonCalculateur monCalculateur = new MonCalculateurCarre();
		System.out.println("4*4=" + monCalculateur.calculer(4));// 4*4=16.0 ou autre
		
		CoordinateurSansSpring coordinateur = new CoordinateurSansSpring();
		coordinateur.calculerEtAfficher();
	}
	
	public static void avecSpring() {
		System.setProperty("spring.profiles.active", "v1,carre");
		//System.setProperty("spring.profiles.active", "v2,carre");
		//System.setProperty("spring.profiles.active", "v2,double");
		ApplicationContext contextSpring = new AnnotationConfigApplicationContext(ExempleConfig.class);
		// contextSpring représente un ensemble de composants pris en charge par spring
		// et qui est initialisé selon une ou plusieurs classes de configuration.
		
		MonCalculateur monCalculateur = contextSpring.getBean(MonCalculateur.class);
		System.out.println("4*4=" + monCalculateur.calculer(4));// 4*4=16.0 ou autre
		
		//l'appel à .getBean() permet de récupérer une référence sur un composant pris en charge
		//par Spring:
		//Coordinateur coordinateurPrisEnChargeParSpring = contextSpring.getBean(Coordinateur.class)contextSpring.getBean(Coordinateur.class);;
		Coordinateur coordinateurPrisEnChargeParSpring = (Coordinateur) contextSpring.getBean("coordinateur");
		coordinateurPrisEnChargeParSpring.calculerEtAfficher();
		
		CoordinateurAvecInjectionParConstructeur coordinateur2PrisEnChargeParSpring =
				(CoordinateurAvecInjectionParConstructeur) 
				         contextSpring.getBean("coordinateurAvecInjectionParConstructeur");
		coordinateur2PrisEnChargeParSpring.calculerEtAfficher();
		
		//Traducteur traducteur = contextSpring.getBean(Traducteur.class);
		Traducteur traducteur = contextSpring.getBean(TraducteurFrancaisAnglais.class);
		//Traducteur traducteur = (Traducteur) contextSpring.getBean("traducteurFrancaisAnglais");
		System.out.println("traduction de vert=" + traducteur.traduire("vert"));
		
		Traducteur traducteur2 = (Traducteur) contextSpring.getBean("traducteurFrancaisEspagnol");
		System.out.println("traduction de vert=" + traducteur2.traduire("vert"));
		
		((AnnotationConfigApplicationContext) contextSpring).close();
	}

}
