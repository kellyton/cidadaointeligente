package services;

import java.util.List;

import play.db.jpa.JPA;

import models.Total;

import extractor.FinancasExtractor;


public class FinancasService {

	public void processar() {
		new FinancasExtractor().execute();
		
	}
	
	public List<Total> getGastos(int tipo, int ano){
		String query = 
				"FROM Total WHERE tipo = :tipo AND ano = :ano " +
				"ORDER BY valor DESC";
		
		List<Total> list = JPA.em().createQuery(query)
				.setParameter("tipo", tipo)
				.setParameter("ano", ano)
				.getResultList();
		return list;
	}
	
	public List<Total> getGastos(int tipo, int ano, int limit){
		String query = 
				"FROM Total WHERE tipo = :tipo AND ano = :ano " +
				"ORDER BY valor DESC";
		
		List<Total> list = JPA.em().createQuery(query)
				.setParameter("tipo", tipo)
				.setParameter("ano", ano)
				.setMaxResults(limit)
				.getResultList();
		return list;
	}
	
	public List<Total> getGastos(int tipo, String nome){
		String query = 
				"FROM Total WHERE tipo = :tipo AND nome = :nome " +
				"ORDER BY ano ASC";
		
		List<Total> list = JPA.em().createQuery(query)
				.setParameter("tipo", tipo)
				.setParameter("nome", nome)
				.getResultList();
		return list;
	}

	public List<Total> getGastos(int tipo) {
		String query = 
				"FROM Total WHERE tipo = :tipo " +
				"ORDER BY ano ASC";
		
		List<Total> list = JPA.em().createQuery(query)
				.setParameter("tipo", tipo)
				.getResultList();
		return list;
	}
}
