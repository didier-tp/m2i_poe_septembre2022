package tp.appliSpring.exemple;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("carre")
@Component
public class MonCalculateurCarre implements MonCalculateur {

	@Override
	public double calculer(double x) {
		return x*x;
	}

}
