package models.saude;

import javax.persistence.Column;
import javax.persistence.Id;

public class UnidadeSaude {
	
	public static final int ESPECIALIDADE_ODONTOLOGICA = 0;
	public static final int FARMACIA_DA_FAMILIA = 1;
	public static final int HOSPITAL = 2 ;
	public static final int POLICLINICA = 3;
	public static final int APOIO_DIAGNOSTICO_TERAPEUTICO = 4;
	public static final int SAUDE_MENTAL = 5;
	public static final int PRONTO_ATENDIMENTO = 6;
	public static final int UNIDADE_BASICA_DE_SAUDE = 7;
	public static final int UNIDADE_SAUDE_DA_FAMILIA = 8;
	public static final int UNIDADE_ESPECIALIZADA = 9;
	
	@Id
	private long id;
	@Column
	private int tipo;
	@Column
	private String tipoNome;
	@Column
	private int rpa;
	@Column
	private int micro_regiao;
	@Column
	private long cnes;
	@Column
	private String unidade;
	@Column
	private String endereco;
	@Column
	private String bairro;
	@Column
	private String especialidades;
	@Column
	private String fone;
	@Column
	private String latitude;
	@Column
	private String longitude;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getTipo() {
		return tipo;
	}
	
	public void setTipo(int tipo){
		this.tipo = tipo;
		switch (tipo){
			case ESPECIALIDADE_ODONTOLOGICA:
				this.tipoNome = "Centro de Especialidades Odontológicas (CEO)";
				break;
			case FARMACIA_DA_FAMILIA:
				this.tipoNome = "Farmácia da Família";
				break;
			case HOSPITAL:
				this.tipoNome = "Hospital"; 
				break;
			case POLICLINICA:
				this.tipoNome = "Policlínica";
				break;
			case APOIO_DIAGNOSTICO_TERAPEUTICO:
				this.tipoNome = "Serviço de Apoio Diagnóstico e Terapêutico (SADT)";
				break;
			case SAUDE_MENTAL:
				this.tipoNome = "Saúde Mental";
				break;
			case PRONTO_ATENDIMENTO:
				this.tipoNome = "Serviço de Pronto Atendimento (SPA)";
				break;
			case UNIDADE_BASICA_DE_SAUDE:
				this.tipoNome = "Unidade Básicas de Saúde (UBS)";
				break;
			case UNIDADE_SAUDE_DA_FAMILIA:
				this.tipoNome = "Unidades Saúde da Família (USF)";
				break;
			case UNIDADE_ESPECIALIZADA:
				this.tipoNome = "Unidades Especializadas";
				break;
		}
	}
	
/*	public void setTipo(int tipo) {
		this.tipo = tipo;
	}*/
	public String getTipoNome() {
		return tipoNome;
	}
	public void setTipoNome(String tipoNome) {
		this.tipoNome = tipoNome;
	}
	public int getRpa() {
		return rpa;
	}
	public void setRpa(int rpa) {
		this.rpa = rpa;
	}
	public int getMicro_regiao() {
		return micro_regiao;
	}
	public void setMicro_regiao(int micro_regiao) {
		this.micro_regiao = micro_regiao;
	}
	public long getCnes() {
		return cnes;
	}
	public void setCnes(long cnes) {
		this.cnes = cnes;
	}
	public String getUnidade() {
		return unidade;
	}
	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getEspecialidades() {
		return especialidades;
	}
	public void setEspecialidades(String especialidades) {
		this.especialidades = especialidades;
	}
	public String getFone() {
		return fone;
	}
	public void setFone(String fone) {
		this.fone = fone;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	
	//private String tipoUnidade;//Apoio diagnóstico e terapia
	//natureza - conveniado ou proprio - hospitais
	
	
	
}
