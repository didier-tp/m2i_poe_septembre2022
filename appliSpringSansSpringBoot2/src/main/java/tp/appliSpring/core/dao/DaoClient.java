package tp.appliSpring.core.dao;

import java.util.List;
import tp.appliSpring.core.entity.Client;

public interface DaoClient {
	 Client findById(Long numCli);
	 Client save(Client Client); //sauvegarde au sens saveOrUpdate
	 List<Client> findAll();
	 void deleteById(Long numCli);
	 //...
}
