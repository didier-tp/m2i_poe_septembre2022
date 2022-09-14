package com.m2i.tp.dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import com.m2i.tp.Personne;

public class DaoPersonneJdbc implements DaoPersonne {
	
	public Connection etablirConnection(){
		ResourceBundle ressources = ResourceBundle.getBundle("paramDB") ; // paramDB.properties
		String driver = ressources.getString("driver"); 
		String chUrl = ressources.getString("url");
		String username = ressources.getString("username"); 
		String password = ressources.getString("password");
		Connection cn=null;
		try {
			Class.forName(driver); 
			cn = DriverManager.getConnection(chUrl,username,password);
		} catch (ClassNotFoundException e) {
			System.err.println("driver jdbc pas trouvé : " + driver);
			//e.printStackTrace();
		} catch (SQLException e) {
			// echec de connexion
			e.printStackTrace();
		}
		return cn;
	}

	@Override
	public List<Personne> findAllPersonnes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Personne findPersonneByNumero(Integer numero) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Personne addPersonne(Personne p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updatePersonne(Personne p) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deletePersonne(Integer numero) {
		// TODO Auto-generated method stub

	}

}
