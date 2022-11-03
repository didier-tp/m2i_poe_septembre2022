package tp.appliSpring.exemple2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import tp.appliSpring.exemple.Coordinateur;
import tp.appliSpring.exemple.ExempleConfig;
import tp.appliSpring.exemple.MonCalculateur;

public class Exemple2App {

	public static void main(String[] args) {
		ApplicationContext contextSpring = new AnnotationConfigApplicationContext(Exemple2Config.class);
		// contextSpring représente un ensemble de composants pris en charge par spring
		// et qui est initialisé selon une ou plusieurs classes de configuration.
		
		//l'appel à .getBean() permet de récupérer une référence sur un composant pris en charge
		//par Spring:
		//Encadreur encadreurPrisEnChargeParSpring = contextSpring.getBean(Encadreur.class);
		Encadreur encadreurPrisEnChargeParSpring = (Encadreur) contextSpring.getBean("encadreurSimple");
		String msgRes = encadreurPrisEnChargeParSpring.encadrer("java_et_spring");
		System.out.println("msgRes=" + msgRes);
		
		Encadreur encadreur2PrisEnChargeParSpring = (Encadreur) contextSpring.getBean("encadreurBasic");
		String msgRes2 = encadreur2PrisEnChargeParSpring.encadrer("java_et_spring");
		System.out.println("msgRes2=" + msgRes2);
		
		((AnnotationConfigApplicationContext) contextSpring).close();

	}

}
