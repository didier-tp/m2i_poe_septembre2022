package tp.appliSpring.exemple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component // équivalent à @Component("coordinateurAvecInjectionParConstructeur")
           // car par défaut le nom/id du composant est nom classe java avec première lettre en minuscule
//@Component("coordinateurQueJaime")
public class CoordinateurAvecInjectionParConstructeur {
    
	private MonAfficheur monAfficheur = null; // référence vers afficheur à injecter
	
	private MonCalculateur monCalculateur = null;// référence vers calculateur à injecter
	
	//@Autowired implicite si une seule version du constructeur
	public CoordinateurAvecInjectionParConstructeur(MonAfficheur monAfficheur,MonCalculateur monCalculateur){
	    this.monAfficheur = monAfficheur;
	    this.monCalculateur=monCalculateur;
	}

	public void calculerEtAfficher() {
		double x = 4;
		double res = monCalculateur.calculer(x); // x*x ou bien 2*x ou bien ...
		monAfficheur.afficher("res=" + res);// >> res=16 en v1 ou bien ** res=16
	}
}
