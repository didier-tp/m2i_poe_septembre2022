package tp.appliSpring.core.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import tp.appliSpring.core.entity.Compte;

public interface DaoCompte extends JpaRepository<Compte,Long> {
	List<Compte> findByCustomerNumber(Long numCli);
	Compte findWithOperationsById(Long numCompte);
}
