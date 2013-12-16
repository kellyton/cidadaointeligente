package services;

import java.util.List;

import models.educacao.Escola;
import models.saude.UnidadeSaude;
import models.saude.Vacina;
import play.db.jpa.JPA;
import play.db.jpa.Transactional;
import extractor.EducacaoExtractor;
import extractor.SaudeExtractor;

import static models.saude.UnidadeSaude.*;

public class SaudeService {

	public void processar() {
		new SaudeExtractor().execute();
	}
	
	@Transactional
	public List<UnidadeSaude> getUnidadesSaude(long tipo){
		
		if (tipo == TODAS) {
			String query = "FROM UnidadeSaude ORDER BY unidade ASC";
			List<UnidadeSaude> list = JPA.em().createQuery(query)
					.getResultList();
			
			return list;
		} else {
			int nTipo = (int)tipo;
			String query = "FROM UnidadeSaude WHERE tipo = :tipo ORDER BY unidade ASC";
			List<UnidadeSaude> list = JPA.em().createQuery(query)
					.setParameter("tipo", nTipo)
					.getResultList();
			
			return list;
		}
		
	}

	public UnidadeSaude getUnidadeSaude(long id) {
		return (UnidadeSaude)JPA.em().createQuery("FROM UnidadeSaude WHERE id = :id")
				.setParameter("id", id)
				.getSingleResult();
	}

	public List<Vacina> getVacinas() {
		return JPA.em().createQuery("FROM Vacina ORDER BY 1 ASC")
				.getResultList();
	}
	
}
