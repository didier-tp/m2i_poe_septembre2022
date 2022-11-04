package tp.traducteur;

public class TraducteurFrancaisAnglais implements Traducteur {

	@Override
	public String traduire(String texte) {
		String res=null;
		switch(texte) {
		case "rouge" :
			res="red"; break;
		case "vert" :
			res="green"; break;
		case "bleu" :
			res="blue"; break;
		case "jaune" :
			res="yellow"; break;
		}
		return res;
	}

}
