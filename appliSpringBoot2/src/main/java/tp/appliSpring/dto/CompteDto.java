package tp.appliSpring.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString @NoArgsConstructor
public class CompteDto {
	private Long numero;
	private String label;
	private Double solde;
	//...
	public CompteDto(Long numero, String label, Double solde) {
		super();
		this.numero = numero;
		this.label = label;
		this.solde = solde;
	}
	
}

//+autre future classe CompteDtoAvecDetails avec operations en plus
