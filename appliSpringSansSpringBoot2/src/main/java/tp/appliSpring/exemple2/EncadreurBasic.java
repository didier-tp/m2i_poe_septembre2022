package tp.appliSpring.exemple2;

import org.springframework.stereotype.Component;

@Component
public class EncadreurBasic implements Encadreur {
	
	private Prefixeur prefixeur;
	
	private Suffixeur suffixeur;

	@Override
	public String encadrer(String message) {
		return suffixeur.suffixer(prefixeur.prefixer(message));
	}

	//injection par constructeur , puis test dans Exemple2App
	//@Autowired //@Autowired implicite si un seul constructeur
	public EncadreurBasic(Prefixeur prefixeur , Suffixeur suffixeur) {
		this.prefixeur=prefixeur;
		this.suffixeur=suffixeur;
	}
	
	
	

}
