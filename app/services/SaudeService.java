package services;

import java.util.List;

import org.joda.time.DateTime;

import models.educacao.Escola;
import models.saude.ContatoVacina;
import models.saude.DosePessoa;
import models.saude.UnidadeSaude;
import models.saude.Vacina;
import models.saude.VacinaBak;
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
		return JPA.em().createQuery("FROM Vacina ORDER BY diaDeAplicacao ASC")
				.getResultList();
	}
	
	public List<VacinaBak> getVacinasBak() {
		return JPA.em().createQuery("FROM VacinaBak ORDER BY 1 ASC")
				.getResultList();
	}
	
	public ContatoVacina getUsuario(long id) {
		return (ContatoVacina)JPA.em().createQuery("FROM ContatoVacina WHERE id = :id")
				.setParameter("id", id)
				.getSingleResult();
	}
	
	public List<ContatoVacina> getUsuarios(String email) {
		return JPA.em().createQuery("FROM ContatoVacina WHERE email = :id")
				.setParameter("id", email)
				.getResultList();
	}
	
	public List<DosePessoa> getDoses(ContatoVacina user) {
		return JPA.em().createQuery("FROM DosePessoa WHERE pessoa_id = :id")
				.setParameter("id", user.getId())
				.getResultList();
	}

	public List<DosePessoa> getProximasDoses(ContatoVacina user, int limit) {
		return JPA.em().createQuery("FROM DosePessoa WHERE pessoa_id = :id AND dataPrevista > NOW()" +
				" ORDER BY dataPrevista ASC")
				.setParameter("id", user.getId())
				.setMaxResults(limit)
				.getResultList();
	}

	public List<DosePessoa> getUltimasDoses(ContatoVacina user, int limit) {
		return JPA.em().createQuery("FROM DosePessoa WHERE pessoa_id = :id AND dataPrevista < NOW()" +
				" ORDER BY dataPrevista DESC")
				.setParameter("id", user.getId())
				.setMaxResults(limit)
				.getResultList();
	}

	//Retorna as proximas doses ainda nao avisadas ao usuario, nas proximas x horas
	public List<DosePessoa> getProximasDoses(int horas) {
		DateTime dataIni = new DateTime().minusHours(horas);
		DateTime dataFim = new DateTime().plusHours(horas);
		
		return JPA.em().createQuery("FROM DosePessoa" +
				" WHERE dataPrevista > :dIni" +
				" AND dataPrevista < :dFim" + 
				" AND dataAlertado is null" + 
				" ORDER BY dataPrevista ASC")
				.setParameter("dIni", dataIni.toDate())
				.setParameter("dFim", dataFim.toDate())
				.getResultList();
	}
	
}
