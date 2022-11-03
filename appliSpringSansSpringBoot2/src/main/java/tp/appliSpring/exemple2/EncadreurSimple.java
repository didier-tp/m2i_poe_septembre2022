package tp.appliSpring.exemple2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EncadreurSimple implements Encadreur {
	
	@Autowired
	private Prefixeur prefixeur;
	
	@Autowired
	private Suffixeur suffixeur;

	@Override
	public String encadrer(String message) {
		return suffixeur.suffixer(prefixeur.prefixer(message));
	}

}
