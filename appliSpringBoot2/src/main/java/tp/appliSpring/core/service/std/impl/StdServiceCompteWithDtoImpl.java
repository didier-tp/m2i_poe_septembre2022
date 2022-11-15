package tp.appliSpring.core.service.std.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tp.appliSpring.converter.MyGenericConverter;
import tp.appliSpring.core.dao.DaoCompte;
import tp.appliSpring.core.entity.Compte;
import tp.appliSpring.core.service.std.StdServiceCompteWithDto;
import tp.appliSpring.dto.CompteDto;

@Service
@Transactional
public class StdServiceCompteWithDtoImpl 
  extends AbstractGenericStdServiceWithDto<CompteDto,Long,Compte,DaoCompte> 
  implements StdServiceCompteWithDto{
	
	private DaoCompte daoCompte; //for specific methods of this class
	
	@Autowired
	public StdServiceCompteWithDtoImpl(DaoCompte daoCompte) {
		super(CompteDto.class, Compte.class, daoCompte);
		this.daoCompte=daoCompte;
	}

	@Override
	public List<CompteDto> searchCustomerAccounts(Long numClient) {
		return MyGenericConverter.map(daoCompte.findByClientsNumero(numClient),CompteDto.class);
	}

	@Override
	public List<CompteDto> searchAccountsWithMinimumBalance(Double soldeMini) {
		return MyGenericConverter.map(daoCompte.findBySoldeGreaterThanEqual(soldeMini),CompteDto.class);
	};

}
