package tp.appliSpring.core.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import tp.appliSpring.core.entity.Compte;

//@Component
@Repository //@Repository signifie @Component de type DAO (Data Access Object)
public class DaoCompteSimu implements DaoCompte {
	
	private Map<Long,Compte> mapComptes = new HashMap<>();
	private Long maxNum = 0L;
	
	public DaoCompteSimu() {
		mapComptes.put(1L, new Compte(1L,"compteA",50.0));
		mapComptes.put(2L, new Compte(2L,"compteB",100.0));
		mapComptes.put(3L, new Compte(3L,"compteC",150.0));
		maxNum= 3L;
	}

	@Override
	public Compte findById(Long numCpt) {
		return mapComptes.get(numCpt);
	}

	@Override
	public Compte save(Compte compte) {
		if(compte.getNumero()==null) {
			maxNum++;
			compte.setNumero(maxNum);
		}
		mapComptes.put(compte.getNumero(), compte);
		return compte;
	}

	@Override
	public List<Compte> findAll() {
		return (List<Compte>) mapComptes.values();
	}

	@Override
	public void deleteById(Long numCpt) {
		mapComptes.remove(numCpt);
	}

	@Override
	public List<Compte> findByCustomerNumber(Long numCli) {
		// TODO Auto-generated method stub
		return null;
	}

}
