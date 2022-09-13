package com.m2i.tp.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.m2i.tp.Personne;

public class DaoPersonneSimu implements DaoPersonne {
	
	private Integer maxNumPers=0;  //pour simuler une auto incrementation
	private Map<Integer,Personne> mapPersonnes = new HashMap<>();

	@Override
	public List<Personne> findAllPersonnes() {
		return new ArrayList<Personne>(this.mapPersonnes.values());
	}

	@Override
	public Personne findPersonneByNumero(Integer numero) {
		return this.mapPersonnes.get(numero);
	}

	@Override
	public Personne addPersonne(Personne p) {
		if(p.getNumero()==null) {
			maxNumPers++; //simuler auto-incrémentation
			p.setNumero(maxNumPers);
		}
		this.mapPersonnes.put(p.getNumero(), p);
		return p;
	}

	@Override
	public void updatePersonne(Personne p) {
		this.mapPersonnes.put(p.getNumero(), p);
	}

	@Override
	public void deletePersonne(Integer numero) {
		this.mapPersonnes.remove(numero);
	}


}
