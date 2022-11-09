package tp.appliSpring.core.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import tp.appliSpring.core.entity.Client;

/*
 * Avec l'extension Spring-Data , plus besoin de coder les classes
 * d'implémentation "Dao...Jpa" en se basant sur EntityManager
 * car ce code est généré automatiquement .
 */


public interface DaoClient extends JpaRepository<Client,Long> {
	/* méthodes héritées de JpaRepository / CrudRepository :
	 Optional<Client> findById(Long numCli);
	 Client save(Client Client); 
	 List/Iterable<Client> findAll();
	 void deleteById(Long numCli);
	 */
	
	//méthode de recherche spécifique codée ici via
	//@NameQuery de nom = Client.findWithAccountById
	Client findWithAccountById(long numeroCli);
}
