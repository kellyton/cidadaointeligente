package models.saude;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Vacina {
	
	public static final int DOSE_UNICA = 0;
	public static final int DOSE_PRIMEIRA = 1;
	public static final int DOSE_SEGUNDA = 2;
	public static final int DOSE_TERCEIRA = 3;
	public static final int DOSE_REFORCO = 4;
	public static final int DOSE_PERIODICA = 99;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column
	private String idade;
	@Column
	private String vacina;
	@Column(columnDefinition = "TEXT")
	private String doencaProtecao;
	@Column
	private String dose;
	@Column
	private String doseQtd;
	@Column
	private String viaAdministracao;
	@Column
	private int tipo;
	@Column
	private int diaDeAplicacao;

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

	public String getDoencaProtecao() {
		return doencaProtecao;
	}

	public void setDoencaProtecao(String doencaProtecao) {
		this.doencaProtecao = doencaProtecao;
	}

	public String getDose() {
		return dose;
	}

	public void setDose(String dose) {
		this.dose = dose;
	}

	public String getDoseQtd() {
		return doseQtd;
	}

	public void setDoseQtd(String doseQtd) {
		this.doseQtd = doseQtd;
	}

	public String getViaAdministracao() {
		return viaAdministracao;
	}

	public void setViaAdministracao(String viaAdministracao) {
		this.viaAdministracao = viaAdministracao;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public int getDiaDeAplicacao() {
		return diaDeAplicacao;
	}

	public void setDiaDeAplicacao(int diaDeAplicacao) {
		this.diaDeAplicacao = diaDeAplicacao;
	}

	@Override
	public String toString() {
		return vacina + " - " + idade;
	}
}
