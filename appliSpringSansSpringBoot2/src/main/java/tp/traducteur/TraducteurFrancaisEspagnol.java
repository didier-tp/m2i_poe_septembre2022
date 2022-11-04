package tp.traducteur;

/*
 * on va considérer que le package tp.traducteur soit potentiellement codé
 * dans un autre projet . On a accès (via maven) au code compilé
 * mais on a pas accès au code source .
 * impossible d'ajouter @Component
 */

public class TraducteurFrancaisEspagnol implements Traducteur {
	
	private boolean enMaj = false;
	

	public void setEnMaj(boolean enMaj) {
		this.enMaj = enMaj;
	}


	@Override
	public String traduire(String texte) {
		String res=null;
		switch(texte) {
		case "rouge" :
			res="rojo"; break;
		case "vert" :
			res="verde"; break;
		case "bleu" :
			res="azul"; break;
		case "jaune" :
			res="amarillo"; break;
		}
		//return enMaj?res.toUpperCase():res;
		if(enMaj)
			return res.toUpperCase();
		else
			return res;
	}

}
