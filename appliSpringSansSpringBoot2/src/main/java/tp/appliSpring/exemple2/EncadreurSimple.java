package tp.appliSpring.exemple2;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import tp.traducteur.Traducteur;

@Component
public class EncadreurSimple implements Encadreur {
	
	@Autowired
	//@Inject  //@Inject = standard java qui peut être interprété comme @Autowired
	           //si la dépendance javax.inject est ajoutée dans pom.xml
	private Prefixeur prefixeur;
	
	@Autowired //@Autowired effectue une injection selon le type (compatible avec interface)
	//@Resource(name = "suffixeurV1")  
	//@Resource(name="...") fait d'abord une injection selon le nom logique précisé
	//et @Resource() fait une injection par type comme @Autowired
	private Suffixeur suffixeur;
	
	@Autowired
	private Traducteur traducteur;

	@Override
	public String encadrer(String message) {
		String msgTrad=traducteur.traduire(message);
		if(msgTrad!=null ) message=msgTrad;
		return suffixeur.suffixer(prefixeur.prefixer(message));
	}

	public EncadreurSimple() {
		//super();
		System.out.println("dans constructeur, prefixeur="+prefixeur);//null
	}
	
	@PostConstruct
	public void initialiser() {
		System.out.println("dans méthode préfixée par @PostConstruct, prefixeur="+prefixeur);//pas null
	}
	

}
