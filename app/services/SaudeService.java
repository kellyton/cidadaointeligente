package services;

import java.util.List;

import models.educacao.Escola;
import models.saude.UnidadeSaude;
import play.db.jpa.JPA;
import play.db.jpa.Transactional;
import extractor.EducacaoExtractor;
import extractor.SaudeExtractor;


public class SaudeService {

	public void processar() {
		new SaudeExtractor().execute();
		
	}

	@Transactional
	public List<UnidadeSaude> getUnidadesSaude(){
		String query = "FROM UnidadeSaude ORDER BY unidade ASC";
		
		List<UnidadeSaude> list = JPA.em().createQuery(query)
				.getResultList();
		return list;
	}

	public UnidadeSaude getUnidadeSaude(long id) {
		return (UnidadeSaude)JPA.em().createQuery("FROM UnidadeSaude WHERE id = :id")
				.setParameter("id", id)
				.getSingleResult();
	}
	
}
