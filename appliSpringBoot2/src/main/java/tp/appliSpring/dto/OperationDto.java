package tp.appliSpring.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @NoArgsConstructor @ToString
public class OperationDto {
	private String label;
	private Double montant;
	private String date;
	
	public OperationDto(String label, Double montant, String date) {
		super();
		this.label = label;
		this.montant = montant;
		this.date = date;
	}
	
	
	
}
