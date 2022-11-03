package tp.appliSpring.exemple2;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("v2")
@Component
public class PrefixeurV2 implements Prefixeur {
	
	public static final String PREFIXE="**";

	@Override
	public String prefixer(String message) {
		return PREFIXE + message;
	}

}
