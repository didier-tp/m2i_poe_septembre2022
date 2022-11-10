package tp.appliSpring.core.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import tp.appliSpring.core.entity.Compte;

public interface DaoCompte extends JpaRepository<Compte,Long> {

	//codé via @NamedQuery "Compte.findByCustomerNumber"
	List<Compte> findByCustomerNumber(Long numCli);
	
	
	//NB: le code de la requête déclenchée sera généré automatiquement
	//par spring-Data via des conventions de nom de méthode (même pas besoin de @NamedQuery)
	//findBy+Clients+Numero où clients vient du fait qu'il existe .clients dans classe Compte
	// et numero vient du fait qu'il existe .numero dans la classe Client
	List<Compte> findByClientsNumero(Long numCli);
	
	//codé via convention de nommage
	List<Compte> findBySoldeGreaterThanEqualOrderBySoldeAsc(double soldeMini);
	List<Compte> findBySoldeGreaterThanEqual(double soldeMini);
	
	//codé via @NamedQuery "Compte.findWithOperationsById"
	Compte findWithOperationsById(Long numCompte);
}
