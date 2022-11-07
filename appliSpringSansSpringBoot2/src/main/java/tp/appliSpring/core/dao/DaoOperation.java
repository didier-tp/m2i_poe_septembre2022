package tp.appliSpring.core.dao;

import java.util.List;
import tp.appliSpring.core.entity.Operation;

public interface DaoOperation {
	 Operation findById(Long numOp);
	 Operation save(Operation Operation); //sauvegarde au sens saveOrUpdate
	 List<Operation> findAll();
	 void deleteById(Long numOp);
	//...
	List<Operation> findByAccountNumber(Long numCpt);
}
