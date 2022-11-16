package tp.appliSpring.core.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tp.appliSpring.core.dao.DaoCompte;
import tp.appliSpring.core.dao.DaoOperation;
import tp.appliSpring.core.entity.Compte;
import tp.appliSpring.core.entity.Operation;
import tp.appliSpring.core.exception.NotFoundException;
import tp.appliSpring.core.exception.SoldeInsuffisantException;

//@Component
@Transactional //souvent tout en haut de la classe sur un vrai projet d'entreprise
@Service //@Service signifie @Component de type service métier (business service)
public class ServiceCompteImpl implements ServiceCompte {
	
	//@Autowired ici ou bien implicitement sur constructeur
	//@Qualifier("daoCompteJpa")
	private DaoCompte daoCompte;
	
	//@Autowired ici ou bien implicitement sur constructeur
	private DaoOperation daoOperation; 
	
	//@Autowired //@Autowired implicite si un seul constructeur
	/* ancienne version sans spring-data : public ServiceCompteImpl(@Qualifier("daoCompteJpa")DaoCompte daoCompte , DaoOperation daoOperation) { */
	public ServiceCompteImpl(DaoCompte daoCompte , DaoOperation daoOperation) {
		this.daoCompte = daoCompte;
		this.daoOperation = daoOperation;
		System.out.println("dans le constructeur ServiceCompteImpl effectuant l'injection de dépendance ");
		System.out.println("daoCompte="+daoCompte  + " " + daoCompte.getClass().getName()+" et daoOperation=" + daoOperation);
	}

	@Override
	public Compte rechercherCompteParNumero(long numero) throws NotFoundException{
		//return daoCompte.findById(numero).orElse(null);
		Optional<Compte> optCompte = daoCompte.findById(numero);
		if(optCompte.isEmpty())
			throw new NotFoundException("compte not found avec numero="+numero);
		/*else*/
		return optCompte.get();
	}

	@Override
	public List<Compte> rechercherTousComptes() {
		return daoCompte.findAll();
	}

	@Override
	public List<Compte> rechercherComptesDuClient(long numClient) {
		//return daoCompte.findByCustomerNumber(numClient); //ancien nom avec @NamedQuery
		return daoCompte.findByClientsNumero(numClient);
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
			Compte cptDeb = daoCompte.findById(numCptDeb).orElse(null);
			if(cptDeb.getSolde() < montant)
				throw new SoldeInsuffisantException("solde insuffisant sur compte " + numCptDeb);
			cptDeb.setSolde(cptDeb.getSolde() - montant);
			//créer, rattacher et enregistrer un objet Operation sur de débit
			Operation opDebit = new Operation(null, "debit suite au virement", -montant);
			opDebit.setCompte(cptDeb);	daoOperation.save(opDebit);
			daoCompte.save(cptDeb); //v1 ou v2 sans .save() si @Transactional
			
			Compte cptCred = daoCompte.findById(numCptCred).orElse(null);
			cptCred.setSolde(cptCred.getSolde() + montant);
			//créer, rattacher et enregistrer un objet Operation sur le crédit
			Operation opCredit = new Operation(null, "crédit suite au virement", montant);
			opCredit.setCompte(cptCred);	daoOperation.save(opCredit);
			daoCompte.save(cptCred); // v1  ou v2 sans .save() si @Transactional
		} catch (SoldeInsuffisantException e) {
			throw e;
		}
		catch (Exception e) {
			throw new RuntimeException("echec virement" , e);
		}
	}

	@Override
	public Compte rechercherCompteAvecOperationsParNumero(Long numCompteXy) {
		return daoCompte.findWithOperationsById(numCompteXy);
	}

	@Override
	public List<Compte> rechercherComptesViaSoldeMini(double soldeMini) {
		//return daoCompte.findBySoldeGreaterThanEqual(soldeMini); //pas triés
		return daoCompte.findBySoldeGreaterThanEqualOrderBySoldeAsc(soldeMini); //triés par soldes croissants
	}

	@Override
	public boolean verifierPasDecouvert(long numCpt) throws NotFoundException {
		Compte compte = daoCompte.findById(numCpt).orElse(null);
		if(compte==null)
			throw new NotFoundException("compte inexistant avec numero="+numCpt);
		return (compte.getSolde() >= 0 );
	}

}
