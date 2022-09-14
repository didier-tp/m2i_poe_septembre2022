package com.m2i.tp.app;

import java.sql.Connection;

import com.m2i.tp.dao.DaoPersonneJdbc;

public class App6 {

	public static void main(String[] args) {
		DaoPersonneJdbc daoPersonne  = new DaoPersonneJdbc();
        Connection cn = daoPersonne.etablirConnection();
        System.out.println("connection jdbc = " + cn);
	}

}
