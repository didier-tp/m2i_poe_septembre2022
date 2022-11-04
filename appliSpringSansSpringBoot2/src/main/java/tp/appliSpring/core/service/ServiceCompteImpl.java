package tp.appliSpring.core.service;

import java.util.List;

import tp.appliSpring.core.dao.DaoCompte;
import tp.appliSpring.core.entity.Compte;

//....
public class ServiceCompteImpl implements ServiceCompte {
	
	//...
	private DaoCompte daoCompte;

	@Override
	public Compte rechercherCompteParNumero(long numero) {
		return daoCompte.findById(numero);
	}

	@Override
	public List<Compte> rechercherTousComptes() {
		return daoCompte.findAll();
	}

	@Override
	public List<Compte> rechercherComptesDuClient(long numClient) {
		// sera codé plus tard
		return null;
	}

	@Override
	public Compte sauvegarderCompte(Compte compte) {
		return daoCompte.save(compte);
	}

	@Override
	public void supprimerCompte(long numCpt) {
		daoCompte.deleteById(numCpt);
	}

	@Override
	public void transferer(double montant, long numCptDeb, long numCptCred) {
		//sera codé plus tard
	}

}
