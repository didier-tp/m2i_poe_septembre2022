package tp.appliSpring.exemple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import tp.traducteur.Traducteur;

@Component // équivalent à @Component("coordinateur")
           // car par défaut le nom/id du composant est nom classe java avec première lettre en minuscule
//@Component("coordinateurQueJaime")
public class Coordinateur {

    @Autowired /* l'annotation @Autowired demande à Spring de initialiser la référence
    monAfficheur avec une valeur non nulle qui est une référence vers un autre composant
    pris en charge par Spring et qui est compatible avec le type de l'interface
    */
	private MonAfficheur monAfficheur = null; // référence vers afficheur à injecter
	
    @Autowired
	private MonCalculateur monCalculateur = null;// référence vers calculateur à injecter
	
    @Autowired
    @Qualifier("traducteurFrancaisEspagnol") //@Qualifier() en version spring
              //permet de choisir la version que l'on veut injecter
    private Traducteur traducteur = null;
    
	public Coordinateur(){
	}

	public void calculerEtAfficher() {
		double x = 4;
		double res = monCalculateur.calculer(x); // x*x ou bien 2*x ou bien ...
		monAfficheur.afficher("res=" + res);// >> res=16 en v1 ou bien ** res=16
		System.out.println("traduction de rouge=" + traducteur.traduire("rouge"));
	}
}
