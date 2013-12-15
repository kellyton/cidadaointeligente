package services;

import java.util.List;

import models.educacao.Escola;
import play.db.jpa.JPA;
import play.db.jpa.Transactional;
import extractor.EducacaoExtractor;


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
	
}
