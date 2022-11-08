package tp.appliSpring.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tp.appliSpring.core.dao.DaoCompte;
import tp.appliSpring.core.entity.Compte;
import tp.appliSpring.core.exception.SoldeInsuffisantException;

//@Component
@Transactional //souvent tout en haut de la classe sur un vrai projet d'entreprise
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
	//@Transactional(/* propagation = Propagation.REQUIRED par défaut */)
	//maintenant @Transactional est placé dans le haut de la classe
	public void transferer(double montant, long numCptDeb, long numCptCred) {
		try {
			Compte cptDeb = daoCompte.findById(numCptDeb);
			if(cptDeb.getSolde() < montant)
				throw new SoldeInsuffisantException("solde insuffisant sur compte " + numCptDeb);
			cptDeb.setSolde(cptDeb.getSolde() - montant);
			daoCompte.save(cptDeb); //v1 ou v2 sans .save() si @Transactional
			Compte cptCred = daoCompte.findById(numCptCred);
			cptCred.setSolde(cptCred.getSolde() + montant);
			daoCompte.save(cptCred); // v1  ou v2 sans .save() si @Transactional
		} catch (Exception e) {
			throw new RuntimeException("echec virement" , e);
		}
	}

	@Override
	public Compte rechercherCompteAvecOperationsParNumero(Long numCompteXy) {
		return daoCompte.findWithOperationsById(numCompteXy);
	}

}
