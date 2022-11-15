package tp.appliSpring.core.service;

import java.util.List;

import tp.appliSpring.core.entity.Compte;
import tp.appliSpring.core.exception.NotFoundException;

public interface ServiceCompte {
	Compte rechercherCompteParNumero(long numero) throws NotFoundException;
	List<Compte> rechercherTousComptes();
	List<Compte> rechercherComptesDuClient(long numClient);
	Compte sauvegarderCompte(Compte compte);
	void supprimerCompte(long numCpt);
	boolean verifierPasDecouvert(long numCpt) throws NotFoundException;
	void transferer(double montant, long numCptDeb, long numCptCred);
	Compte rechercherCompteAvecOperationsParNumero(Long numCompteXy);
	List<Compte> rechercherComptesViaSoldeMini(double soldeMini);
}