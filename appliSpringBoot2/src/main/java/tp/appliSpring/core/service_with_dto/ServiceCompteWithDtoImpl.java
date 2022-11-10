package tp.appliSpring.core.service_with_dto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tp.appliSpring.converter.DtoConverter;
import tp.appliSpring.core.entity.Compte;
import tp.appliSpring.core.service.ServiceCompte;
import tp.appliSpring.dto.CompteDto;

@Service
public class ServiceCompteWithDtoImpl implements ServiceCompteWithDto {
	
	@Autowired
	private ServiceCompte serviceCompte;

	@Override
	public CompteDto rechercherCompteParNumero(long numero) {
		return DtoConverter.compteToCompteDto(serviceCompte.rechercherCompteParNumero(numero));
	}

	@Override
	public List<CompteDto> rechercherTousComptes() {
		return DtoConverter.compteListToCompteDtoList(serviceCompte.rechercherTousComptes());
	}

	@Override
	public List<CompteDto> rechercherComptesDuClient(long numClient) {
		return DtoConverter.compteListToCompteDtoList(serviceCompte.rechercherComptesDuClient(numClient));
	}

	@Override
	public Compte sauvegarderCompte(CompteDto compte) {
		return null;
	}

	@Override
	public void supprimerCompte(long numCpt) {
		serviceCompte.supprimerCompte(numCpt);
	}

	@Override
	public void transferer(double montant, long numCptDeb, long numCptCred) {
		serviceCompte.transferer(montant, numCptDeb, numCptCred);
	}

	@Override
	public List<CompteDto> rechercherComptesViaSoldeMini(double soldeMini) {
		return DtoConverter.compteListToCompteDtoList(serviceCompte.rechercherComptesViaSoldeMini(soldeMini));
	}

}
