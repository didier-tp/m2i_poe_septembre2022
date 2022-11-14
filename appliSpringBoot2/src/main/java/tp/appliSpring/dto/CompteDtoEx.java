package tp.appliSpring.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//version étendue de CompteDto avec plus de détails

@Getter @Setter @NoArgsConstructor @ToString
public class CompteDtoEx extends CompteDto {
	private List<OperationDto> operations = new ArrayList<>();
	//...

	public CompteDtoEx(Long numero, String label, Double solde) {
		super(numero, label, solde);
	}
	
	public CompteDtoEx(Long numero, String label, Double solde,List<OperationDto> operations) {
		super(numero, label, solde);
		this.operations=operations;
	}
	
}
