package tp.appliSpring.exemple;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("v2")
@Component
public class MonAfficheurV2 implements MonAfficheur {

	@Override
	public void afficher(String message) {
		System.out.println("** " + message);

	}

	@Override
	public void afficherMaj(String message) {
		System.out.println("** " + message.toUpperCase());
	}

}
