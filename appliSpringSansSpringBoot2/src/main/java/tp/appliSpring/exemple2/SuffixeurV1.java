package tp.appliSpring.exemple2;

import org.springframework.stereotype.Component;

@Component
public class SuffixeurV1 implements Suffixeur {
	
 public static final String SUFFIXE="##";

	@Override
	public String suffixer(String message) {
		return message + SUFFIXE;
	}

}
