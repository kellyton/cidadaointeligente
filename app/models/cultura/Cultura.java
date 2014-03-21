package models.cultura;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Cultura {

	public static final int MERCADO_PUBLICO = 0;
	public static final int FEIRA_LIVRE = 1;
	public static final int SHOPPING = 2 ;
	public static final int TEATRO = 3;
	public static final int MUSEU = 4;
	public static final int PONTE = 5;
	
	public static final int TODAS = 99;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column
	private String nome;
	@Column
	private int tipo;
	@Column
	private String tipoNome;
	@Column
	private String endereco;
	@Column
	private String bairro;
	@Column(columnDefinition="TEXT")
	private String descricao;
	@Column
	private String fone;
	@Column
	private String latitude;
	@Column
	private String longitude;
	@Column
	private String extraInfo;
	
	public String getEnderecoCompleto(){
		if (endereco != null && endereco.trim().length() > 0) {
			return endereco + ", " + bairro;
		} else {
			return bairro;
		}
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getTipo() {
		return tipo;
	}
	public void setTipo(int tipo) {
		this.tipo = tipo;
		switch (tipo){
			case MERCADO_PUBLICO:
				this.tipoNome = "Mercado Público";
				break;
			case FEIRA_LIVRE:
				this.tipoNome = "Feira Livre";
				break;
			case SHOPPING:
				this.tipoNome = "Shopping";
				break;
			case TEATRO:
				this.tipoNome = "Teatro";
				break;
			case MUSEU:
				this.tipoNome = "Museu";
				break;
			case PONTE:
				this.tipoNome = "Ponte";
				break;
		}
	}
	public String getTipoNome() {
		return tipoNome;
	}
	public void setTipoNome(String tipoNome) {
		this.tipoNome = tipoNome;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getBairro() {
		if (bairro != null && !bairro.trim().isEmpty()) {
			return bairro;
		} else {
			return endereco;
		}
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getFone() {
		return fone;
	}
	public void setFone(String fone) {
		this.fone = fone;
	}
	public String getLatitude() {
		return latitude.replace(",", ".");
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude.replace(",", ".");
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getExtraInfo() {
		return extraInfo;
	}
	public void setExtraInfo(String extraInfo) {
		this.extraInfo = extraInfo;
	}
	@Override
	public String toString(){
		return nome;
	}
}
