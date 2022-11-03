package tp.appliSpring.exemple2;

import org.springframework.stereotype.Component;

//@Component
public class PrefixeurV2 implements Prefixeur {
	
	public static final String PREFIXE="**";

	@Override
	public String prefixer(String message) {
		return PREFIXE + message;
	}

}
