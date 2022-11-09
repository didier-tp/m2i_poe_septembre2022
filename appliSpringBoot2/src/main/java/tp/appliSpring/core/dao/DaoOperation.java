package tp.appliSpring.core.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import tp.appliSpring.core.entity.Operation;

public interface DaoOperation extends JpaRepository<Operation,Long>{
	/*
	//codé via @NamedQuery "Operation.findByAccountNumber"
	List<Operation> findByAccountNumber(Long numCpt);
	*/
	
	//codé via convention de nommage (Operation comporte .compte et Compte comporte .numero )
	List<Operation> findByCompteNumero(Long numCpt);
}
