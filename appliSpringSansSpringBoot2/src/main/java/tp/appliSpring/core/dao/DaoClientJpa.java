package tp.appliSpring.core.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tp.appliSpring.core.entity.Client;

@Repository //ou bien @Component
public class DaoClientJpa implements DaoClient {
	
	@PersistenceContext //pour initialiser entityManager à partir du fichier META-INF/persistence.xml
	                    //ou bien depuis une config équivalente de spring
	private EntityManager entityManager;
	

	@Override
	public Client findById(Long numCpt) {
		return entityManager.find(Client.class, numCpt);
	}
	
	
	@Override
	@Transactional
	public Client save(Client Client) {
		try {
			if(Client.getNumero()==null) {
			    entityManager.persist(Client); //INSERT INTO avec auto_incrementation
			}
			else {
			   entityManager.merge(Client); //UPDATE
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Client; //avec numero pas null (quelquefois auto_incrémenté)
	}


	@Override
	public List<Client> findAll() {
	
		return entityManager.createNamedQuery("Client.findAll", Client.class)
	            .getResultList();

	}

	@Override
	@Transactional
	public void deleteById(Long numCpt) {
		Client Client = entityManager.find(Client.class, numCpt);
		entityManager.remove(Client);
	}

}
