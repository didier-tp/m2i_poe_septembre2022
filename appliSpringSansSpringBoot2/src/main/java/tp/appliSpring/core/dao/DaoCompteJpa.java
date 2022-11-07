package tp.appliSpring.core.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tp.appliSpring.core.entity.Compte;

@Repository //ou bien @Component
public class DaoCompteJpa implements DaoCompte {
	
	@PersistenceContext //pour initialiser entityManager à partir du fichier META-INF/persistence.xml
	                    //ou bien depuis une config équivalente de spring
	private EntityManager entityManager;
	
	/*
	@Autowired
	EntityManagerFactory entityManagerFactory;
	*/

	@Override
	public Compte findById(Long numCpt) {
		return entityManager.find(Compte.class, numCpt);
	}
	
	
	@Override
	@Transactional
	public Compte save(Compte compte) {
		try {
			if(compte.getNumero()==null) {
			    entityManager.persist(compte); //INSERT INTO avec auto_incrementation
			}
			else {
			   entityManager.merge(compte); //UPDATE
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return compte; //avec numero pas null (quelquefois auto_incrémenté)
	}


	/*
	@Override
	public Compte save(Compte compte) {
		EntityManager em = entityManagerFactory.createEntityManager();
		try {
			em.getTransaction().begin();
			if(compte.getNumero()==null) {
			    em.persist(compte); //INSERT INTO avec auto_incrementation
			}
			else {
			   em.merge(compte); //UPDATE
			}
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		}
		em.close();
		return compte; //avec numero pas null (quelquefois auto_incrémenté)
	}
	*/

	@Override
	public List<Compte> findAll() {
		return entityManager.createQuery("SELECT c FROM Compte c", Compte.class)
				            .getResultList();
	}

	@Override
	public void deleteById(Long numCpt) {
		Compte compte = entityManager.find(Compte.class, numCpt);
		entityManager.remove(compte);
	}

}
