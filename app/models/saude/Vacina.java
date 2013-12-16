package models.saude;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Vacina {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column
	private String idade;
	@Column
	private String vacina;
	@Column(columnDefinition = "TEXT")
	private String doenca_protecao;
	@Column
	private String dose;
	@Column
	private String dose_qtd;
	@Column
	private String via_administracao;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getIdade() {
		return idade;
	}

	public void setIdade(String idade) {
		this.idade = idade;
	}

	public String getVacina() {
		return vacina;
	}

	public void setVacina(String vacina) {
		this.vacina = vacina;
	}

	public String getDoenca_protecao() {
		return doenca_protecao;
	}

	public void setDoenca_protecao(String doenca_protecao) {
		this.doenca_protecao = doenca_protecao;
	}

	public String getDose() {
		return dose;
	}

	public void setDose(String dose) {
		this.dose = dose;
	}

	public String getDose_qtd() {
		return dose_qtd;
	}

	public void setDose_qtd(String dose_qtd) {
		this.dose_qtd = dose_qtd;
	}

	public String getVia_administracao() {
		return via_administracao;
	}

	public void setVia_administracao(String via_administracao) {
		this.via_administracao = via_administracao;
	}

	@Override
	public String toString() {
		return vacina + " - " + idade;
	}
}
