package com.m2i.tp.dao;

import java.util.List;
import com.m2i.tp.Personne;

/*
 * DAO = Data Access Object 
 * = objet de traitement qui est spécialisé dans l'accès aux données (en base, ...)
 * comporte des méthodes CRUD (Create , Retreive , Update , Delete)
 * -------------
 * classe DaoPersonneSimu (avec Map en mémoire) implements DaoPersonne
 * classe DaoPersonneJdbc (avec requête SQL) implements DaoPersonne
 */
public interface DaoPersonne {
	
	List<Personne> getAllPersonnes();
	//...

}
