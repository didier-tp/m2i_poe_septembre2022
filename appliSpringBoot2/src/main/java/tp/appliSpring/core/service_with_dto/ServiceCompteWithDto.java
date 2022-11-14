package tp.appliSpring.core.service_with_dto;

import java.util.List;

import tp.appliSpring.core.entity.Compte;
import tp.appliSpring.dto.CompteDto;

public interface ServiceCompteWithDto {
	CompteDto rechercherCompteParNumero(long numero);
	List<CompteDto> rechercherTousComptes();
	List<CompteDto> rechercherComptesDuClient(long numClient);
	CompteDto sauvegarderCompte(CompteDto compte);
	void supprimerCompte(long numCpt);
	void transferer(double montant, long numCptDeb, long numCptCred);
	//Compte rechercherCompteAvecOperationsParNumero(Long numCompteXy);
	List<CompteDto> rechercherComptesViaSoldeMini(double soldeMini);
}