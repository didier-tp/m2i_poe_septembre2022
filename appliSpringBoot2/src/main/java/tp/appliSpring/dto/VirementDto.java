package tp.appliSpring.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString @NoArgsConstructor
public class VirementDto {
	private Double montant;
	private String numCptDeb;
	private String numCptCred;
	
	private Boolean ok;
	private String message;
	//...
	
	public VirementDto(Double montant, String numCptDeb, String numCptCred) {
		super();
		this.montant = montant;
		this.numCptDeb = numCptDeb;
		this.numCptCred = numCptCred;
	}
	
	
}
