package tp.appliSpring.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tp.appliSpring.core.dao.DaoCompte;
import tp.appliSpring.core.entity.Compte;

//@Component
@Service //@Service signifie @Component de type service métier (business service)
public class ServiceCompteImpl implements ServiceCompte {
	
	//@Autowired ici ou bien implicitement sur constructeur
	//@Qualifier("daoCompteJpa")
	private DaoCompte daoCompte;
	
	//@Autowired //@Autowired implicite si un seul constructeur
	public ServiceCompteImpl(@Qualifier("daoCompteJpa")DaoCompte daoCompte) {
		this.daoCompte = daoCompte;
	}

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
		return daoCompte.findByCustomerNumber(numClient);
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
	//@Transactional
	public void transferer(double montant, long numCptDeb, long numCptCred) {
		Compte cptDeb = daoCompte.findById(numCptDeb);
		cptDeb.setSolde(cptDeb.getSolde() - montant);
		daoCompte.save(cptDeb); //v1
		Compte cptCred = daoCompte.findById(numCptCred);
		cptCred.setSolde(cptCred.getSolde() + montant);
		daoCompte.save(cptCred); //v1
	}

	@Override
	public Compte rechercherCompteAvecOperationsParNumero(Long numCompteXy) {
		return daoCompte.findWithOperationsById(numCompteXy);
	}

}
