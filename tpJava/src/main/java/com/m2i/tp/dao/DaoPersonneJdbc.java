package com.m2i.tp.dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
	
	static void closeCn(Connection cn){
		try { cn.close(); } catch (SQLException e) {e.printStackTrace();}
		}

	@Override
	public List<Personne> findAllPersonnes() {
		List<Personne> listePers = new ArrayList<>();
		Connection cn = null; 
		try {
			cn = etablirConnection();
			Statement statement=cn.createStatement();
			String chRequeteSql = "SELECT * FROM personne";
			
			ResultSet rs = statement.executeQuery(chRequeteSql);
			while(rs.next()) {
				listePers.add(new Personne(rs.getInt("numero"),
						                   rs.getString("prenom"),
						                   rs.getString("nom"),
						                   rs.getInt("age")));
			}
			rs.close();
			statement.close();
		}
		catch( SQLException ex) { 
			ex.printStackTrace();
        }
		finally {
			closeCn(cn);
		}
		
		return listePers;
	}

	@Override
	public Personne findPersonneByNumero(Integer numero) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Personne addPersonne(Personne p) {
		Connection cn = null; 
		try {
			cn = etablirConnection();
			String chRequeteSql = "INSERT INTO personne(numero,prenom,nom,age) VALUES(?,?,?,?)";
			PreparedStatement pstatement=cn.prepareStatement(chRequeteSql);
			pstatement.setInt(1, p.getNumero()); //v1 sans 	auto_increment du numero
			pstatement.setString(2, p.getPrenom());
			pstatement.setString(3, p.getNom());
			pstatement.setInt(4, p.getAge());
			pstatement.executeUpdate();
			pstatement.close();
		}
		catch( SQLException ex) { 
			ex.printStackTrace();
        }
		finally {
			closeCn(cn);
		}
		return p;
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
