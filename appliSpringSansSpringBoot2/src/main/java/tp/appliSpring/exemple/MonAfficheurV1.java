package tp.appliSpring.exemple;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("v1") //le @Component sera pris en charge par spring
               //que si le profil "v1" (variante de config) est activé au démarrage de l'appli ou du test
@Component
public class MonAfficheurV1 implements MonAfficheur {

	@Override
	public void afficher(String message) {
		System.out.println(">> " + message);

	}

	@Override
	public void afficherMaj(String message) {
		System.out.println(">> " + message.toUpperCase());
	}

}
