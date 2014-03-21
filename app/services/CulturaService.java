package services;

import java.util.List;

import extractor.CulturaExtractor;

import models.cultura.Cultura;
import play.db.jpa.JPA;
import play.db.jpa.Transactional;
import static models.cultura.Cultura.*;

public class CulturaService {

	public void processar() {
		new CulturaExtractor().execute();
	}
	
	@Transactional
	public List<Cultura> getEquipamentos(long tipo){
		if (tipo == TODAS) {
			String query = "FROM Cultura ORDER BY nome ASC";
			List<Cultura> list = JPA.em().createQuery(query)
					.getResultList();
			
			return list;
		} else {
			int nTipo = (int)tipo;
			String query = "FROM Cultura WHERE tipo = :tipo ORDER BY nome ASC";
			List<Cultura> list = JPA.em().createQuery(query)
					.setParameter("tipo", nTipo)
					.getResultList();
			
			return list;
		}
		
	}

	public Cultura getEquipamento(long id) {
		return (Cultura)JPA.em().createQuery("FROM Cultura WHERE id = :id")
				.setParameter("id", id)
				.getSingleResult();
	}
	
}
