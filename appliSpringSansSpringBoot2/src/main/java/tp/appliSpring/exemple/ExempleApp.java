package tp.appliSpring.exemple;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

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
		ApplicationContext contextSpring = new AnnotationConfigApplicationContext(ExempleConfig.class);
		// contextSpring représente un ensemble de composants pris en charge par spring
		// et qui est initialisé selon une ou plusieurs classes de configuration.
		
		MonCalculateur monCalculateur = contextSpring.getBean(MonCalculateur.class);
		System.out.println("4*4=" + monCalculateur.calculer(4));// 4*4=16.0 ou autre
		
		//l'appel à .getBean() permet de récupérer une référence sur un composant pris en charge
		//par Spring:
		//Coordinateur coordinateurPrisEnChargeParSpring = contextSpring.getBean(Coordinateur.class);
		Coordinateur coordinateurPrisEnChargeParSpring = (Coordinateur) contextSpring.getBean("coordinateur");
		coordinateurPrisEnChargeParSpring.calculerEtAfficher();
		
		((AnnotationConfigApplicationContext) contextSpring).close();
	}

}
