package services;

import java.util.ArrayList;
import java.util.List;

import models.educacao.Escola;
import play.db.jpa.JPA;
import play.db.jpa.Transactional;
import extractor.EducacaoExtractor;
import static models.educacao.Escola.*;

public class EducacaoService {

	public void processar() {
		new EducacaoExtractor().execute();
		
	}

	@Transactional
	public List<Escola> getEscolas(){
		String query = "FROM Escola ORDER BY nome ASC";
		
		List<Escola> list = JPA.em().createQuery(query)
				.getResultList();
		return list;
	}
	
	@Transactional
	public List<Escola> getEscolas(long tipo){
		
		String query = "FROM Escola ORDER BY nome ASC";
		
		List<Escola> list = JPA.em().createQuery(query)
				.getResultList();
		
		if (tipo == TODAS){
			
			return list;
			
		} else {
			List<Escola> finalList = new ArrayList<Escola>();
			
			for (Escola e: list){
				if (e.getTipo() == tipo){
					finalList.add(e);
				}
			}
			return finalList;
		}
		
		
	}

	public Escola getEscola(long id) {
		return (Escola)JPA.em().createQuery("FROM Escola WHERE codigoEscola = :id")
				.setParameter("id", id)
				.getSingleResult();
	}
	
}
