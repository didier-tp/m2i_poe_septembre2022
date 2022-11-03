package tp.appliSpring.exemple2;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("v1")
@Component
public class PrefixeurV1 implements Prefixeur {
	
	public static final String PREFIXE="##";

	@Override
	public String prefixer(String message) {
		return PREFIXE + message;
	}

}
