package tp.appliSpring.exemple;

public class CoordinateurSansSpring {
	// …
	private MonAfficheur monAfficheur = null; // référence vers afficheur à injecter
	// ...
	private MonCalculateur monCalculateur = null;// référence vers calculateur à injecter
	
	public CoordinateurSansSpring(){
		//this.monAfficheur = new MonAfficheurV1();
		this.monAfficheur = new MonAfficheurV2();
		//this.monCalculateur = new MonCalculateurCarre();
		this.monCalculateur = new MonCalculateurDouble();
	}

	public void calculerEtAfficher() {
		double x = 4;
		double res = monCalculateur.calculer(x); // x*x ou bien 2*x ou bien ...
		monAfficheur.afficher("res=" + res);// >> res=16 en v1 ou bien ** res=16
	}
}
