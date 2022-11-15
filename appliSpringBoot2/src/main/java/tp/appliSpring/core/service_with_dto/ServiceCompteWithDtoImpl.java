package tp.appliSpring.core.service_with_dto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tp.appliSpring.converter.DtoConverter;
import tp.appliSpring.converter.MyGenericConverter;
import tp.appliSpring.core.entity.Compte;
import tp.appliSpring.core.service.ServiceCompte;
import tp.appliSpring.dto.CompteDto;

@Service
public class ServiceCompteWithDtoImpl implements ServiceCompteWithDto {
	
	@Autowired
	private ServiceCompte serviceCompte;

	@Override
	public CompteDto rechercherCompteParNumero(long numero) {
		return MyGenericConverter.map(serviceCompte.rechercherCompteParNumero(numero), CompteDto.class);
		//return DtoConverter.compteToCompteDto(serviceCompte.rechercherCompteParNumero(numero));
	}

	@Override
	public List<CompteDto> rechercherTousComptes() {
		return MyGenericConverter.map(serviceCompte.rechercherTousComptes(), CompteDto.class);
		//return DtoConverter.compteListToCompteDtoList(serviceCompte.rechercherTousComptes());
	}

	@Override
	public List<CompteDto> rechercherComptesDuClient(long numClient) {
		return MyGenericConverter.map(serviceCompte.rechercherComptesDuClient(numClient), CompteDto.class);
		//return DtoConverter.compteListToCompteDtoList(serviceCompte.rechercherComptesDuClient(numClient));
	}

	@Override
	public CompteDto sauvegarderCompte(CompteDto compteDto) {
		Compte compteEntity = MyGenericConverter.map(compteDto,Compte.class);
		serviceCompte.sauvegarderCompte(compteEntity);
		compteDto.setNumero(compteEntity.getNumero());
		return compteDto;
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
		return MyGenericConverter.map(serviceCompte.rechercherComptesViaSoldeMini(soldeMini), CompteDto.class);
		//return DtoConverter.compteListToCompteDtoList(serviceCompte.rechercherComptesViaSoldeMini(soldeMini));
	}

}
