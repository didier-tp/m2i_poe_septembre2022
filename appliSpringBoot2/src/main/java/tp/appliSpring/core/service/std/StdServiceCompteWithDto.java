package tp.appliSpring.core.service.std;

import java.util.List;

import tp.appliSpring.dto.CompteDto;

public interface StdServiceCompteWithDto extends StdGenericServiceWithDto<CompteDto,Long>{

	List<CompteDto> searchCustomerAccounts(Long numClient);

	List<CompteDto> searchAccountsWithMinimumBalance(Double soldeMini);

}
