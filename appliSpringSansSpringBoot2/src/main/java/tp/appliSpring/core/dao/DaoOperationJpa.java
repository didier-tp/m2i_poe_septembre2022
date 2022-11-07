package tp.appliSpring.core.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tp.appliSpring.core.entity.Operation;

@Repository // ou bien @Component
public class DaoOperationJpa implements DaoOperation {

	@PersistenceContext // pour initialiser entityManager à partir du fichier META-INF/persistence.xml
	// ou bien depuis une config équivalente de spring
	private EntityManager entityManager;

	@Override
	public Operation findById(Long numOp) {
		return entityManager.find(Operation.class, numOp);
	}

	@Override
	@Transactional
	public Operation save(Operation operation) {
		if (operation.getNumOp() == null) {
			entityManager.persist(operation); // INSERT INTO avec auto_incrementation
		} else {
			entityManager.merge(operation); // UPDATE
		}
		return operation; // avec numero pas null (quelquefois auto_incrémenté)
	}

	@Override
	public List<Operation> findAll() {
		return entityManager.createNamedQuery("Operation.findAll", Operation.class)
	            .getResultList();
	}

	@Override
	@Transactional
	public void deleteById(Long numOp) {
		Operation op = entityManager.find(Operation.class, numOp);
		entityManager.remove(op);
	}

	@Override
	public List<Operation> findByAccountNumber(Long numCpt) {
		return entityManager.createNamedQuery("Operation.findByAccountNumber", Operation.class)
				.setParameter(1, numCpt) //1 pour paramètre en position 1 = ?1 de la requete
	            .getResultList();
	}

}
