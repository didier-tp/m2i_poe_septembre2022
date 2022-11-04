package tp.appliSpring.core.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import tp.appliSpring.core.entity.Compte;

@Repository //ou bien @Component
public class DaoCompteJpa implements DaoCompte {
	
	@PersistenceContext //pour initialiser entityManager à partir du fichier META-INF/persistence.xml
	                    //ou bien depuis une config équivalente de spring
	private EntityManager entityManager;

	@Override
	public Compte findById(Long numCpt) {
		return entityManager.find(Compte.class, numCpt);
	}

	@Override
	public Compte save(Compte compte) {
		try {
			entityManager.getTransaction().begin();
			if(compte.getNumero()==null) {
			    entityManager.persist(compte); //INSERT INTO avec auto_incrementation
			}
			else {
			   entityManager.merge(compte); //UPDATE
			}
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
		}
		return compte; //avec numero pas null (quelquefois auto_incrémenté)
	}

	@Override
	public List<Compte> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteById(Long numCpt) {
		// TODO Auto-generated method stub

	}

}
