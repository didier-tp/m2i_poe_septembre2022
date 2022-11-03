package tp.appliSpring.exemple2;

import org.springframework.stereotype.Component;

public class SuffixeurV2 implements Suffixeur {
	
 //@Component
 public static final String SUFFIXE="**";

	@Override
	public String suffixer(String message) {
		return message + SUFFIXE;
	}

}
