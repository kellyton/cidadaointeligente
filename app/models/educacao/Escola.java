package models.educacao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Escola {

	public static final int ESCOLA = 1;
	public static final int CRECHE = 2;
	
	public static final int TODAS = 99;
	
	@Id
	private long codigoEscola;
	@Column
	private long inepEscola; //Tem valores duplicados
	@Column
	private String nome;
	@Column
	private String latitude;
	@Column
	private String longitude;
	@Column
	private String cep;
	@Column
	private String endereco;
	@Column
	private String enderecoNumero;
	@Column
	private String enderecoComplemento;
	@Column
	private String bairroCodigo;
	@Column
	private String bairro;
	@Column
	private String telefone;
	@Column
	private String fax;
	@Column
	private String email;
	
	@Column
	private boolean aguaRedePublica;
	@Column
	private boolean esgotoRedePublica;
	@Column
	private boolean lixoColetaPeriodica;
	
	@Column
	private boolean dependenciaLaboratorioInformatica;
	@Column
	private boolean dependenciaLaboratorioCiencias;
	@Column
	private boolean dependenciaBiblioteca;
	@Column
	private boolean dependenciaViasAdequadasDeficiencia;
	@Column
	private boolean dependenciaRefeitorio;
	@Column
	private boolean dependenciaAuditorio;
	@Column
	private boolean dependenciaAreaVerde;
	
	@Column
	private int quantidadeSalasExistentes;
	@Column
	private int quantidadeSalasUtilizadasForaEDentro;

	@Column
	private boolean equipamentosComputadores;
	@Column
	private int computadoresQuantidade;
	@Column
	private int computadoresQuantidadeAlunos;
	@Column
	private boolean acessoInternet;
	@Column
	private boolean bandaLarga;
	
	@Column
	private int totalFuncionarios;
	
	@Column
	private boolean regularCreche;
	@Column
	private boolean regularPreescolar;
	@Column
	private boolean regularFundamental;
	@Column
	private boolean especialFundamental;
	@Column
	private boolean especialEjaFundamental;
	@Column
	private boolean ejaFundamental;
	@Column
	private boolean ejaProjovem;
	
	public int getTipo(){
		if (nome.startsWith("CRECHE")){
			return CRECHE;
		} else {
			return ESCOLA;
		}
	}
	
	public String getTiponOME(){
		if (getTipo() == CRECHE){
			return "Creche";
		} else {
			return "Escola";
		}
	}
	
	public long getInepEscola() {
		return inepEscola;
	}
	public void setInepEscola(long inepEscola) {
		this.inepEscola = inepEscola;
	}
	public long getCodigoEscola() {
		return codigoEscola;
	}
	public void setCodigoEscola(long codigoEscola) {
		this.codigoEscola = codigoEscola;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getLatitude() {
		return latitude.replace(",", ".");
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude.replace(",", ".");
	}
	public String getLongitude() {
		return longitude.replace(",", ".");
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude.replace(",", ".");
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getEndereco() {
		return endereco;
	}
	public String getEnderecoCompleto(){
		return endereco + ", " + enderecoNumero + " " + enderecoComplemento + ", " + bairro + ". CEP: " + cep ;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getEnderecoNumero() {
		return enderecoNumero;
	}
	public void setEnderecoNumero(String enderecoNumero) {
		this.enderecoNumero = enderecoNumero;
	}
	public String getEnderecoComplemento() {
		return enderecoComplemento;
	}
	public void setEnderecoComplemento(String enderecoComplemento) {
		this.enderecoComplemento = enderecoComplemento;
	}
	public String getBairroCodigo() {
		return bairroCodigo;
	}
	public void setBairroCodigo(String bairroCodigo) {
		this.bairroCodigo = bairroCodigo;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public boolean isAguaRedePublica() {
		return aguaRedePublica;
	}
	public void setAguaRedePublica(boolean aguaRedePublica) {
		this.aguaRedePublica = aguaRedePublica;
	}
	public boolean isEsgotoRedePublica() {
		return esgotoRedePublica;
	}
	public void setEsgotoRedePublica(boolean esgotoRedePublica) {
		this.esgotoRedePublica = esgotoRedePublica;
	}
	public boolean isLixoColetaPeriodica() {
		return lixoColetaPeriodica;
	}
	public void setLixoColetaPeriodica(boolean lixoColetaPeriodica) {
		this.lixoColetaPeriodica = lixoColetaPeriodica;
	}
	public boolean isDependenciaLaboratorioInformatica() {
		return dependenciaLaboratorioInformatica;
	}
	public void setDependenciaLaboratorioInformatica(
			boolean dependenciaLaboratorioInformatica) {
		this.dependenciaLaboratorioInformatica = dependenciaLaboratorioInformatica;
	}
	public boolean isDependenciaLaboratorioCiencias() {
		return dependenciaLaboratorioCiencias;
	}
	public void setDependenciaLaboratorioCiencias(
			boolean dependenciaLaboratorioCiencias) {
		this.dependenciaLaboratorioCiencias = dependenciaLaboratorioCiencias;
	}
	public boolean isDependenciaBiblioteca() {
		return dependenciaBiblioteca;
	}
	public void setDependenciaBiblioteca(boolean dependenciaBiblioteca) {
		this.dependenciaBiblioteca = dependenciaBiblioteca;
	}
	public boolean isDependenciaViasAdequadasDeficiencia() {
		return dependenciaViasAdequadasDeficiencia;
	}
	public void setDependenciaViasAdequadasDeficiencia(
			boolean dependenciaViasAdequadasDeficiencia) {
		this.dependenciaViasAdequadasDeficiencia = dependenciaViasAdequadasDeficiencia;
	}
	public boolean isDependenciaRefeitorio() {
		return dependenciaRefeitorio;
	}
	public void setDependenciaRefeitorio(boolean dependenciaRefeitorio) {
		this.dependenciaRefeitorio = dependenciaRefeitorio;
	}
	public boolean isDependenciaAuditorio() {
		return dependenciaAuditorio;
	}
	public void setDependenciaAuditorio(boolean dependenciaAuditorio) {
		this.dependenciaAuditorio = dependenciaAuditorio;
	}
	public boolean isDependenciaAreaVerde() {
		return dependenciaAreaVerde;
	}
	public void setDependenciaAreaVerde(boolean dependenciaAreaVerde) {
		this.dependenciaAreaVerde = dependenciaAreaVerde;
	}
	public int getQuantidadeSalasExistentes() {
		return quantidadeSalasExistentes;
	}
	public void setQuantidadeSalasExistentes(int quantidadeSalasExistentes) {
		this.quantidadeSalasExistentes = quantidadeSalasExistentes;
	}
	public int getQuantidadeSalasUtilizadasForaEDentro() {
		return quantidadeSalasUtilizadasForaEDentro;
	}
	public void setQuantidadeSalasUtilizadasForaEDentro(
			int quantidadeSalasUtilizadasForaEDentro) {
		this.quantidadeSalasUtilizadasForaEDentro = quantidadeSalasUtilizadasForaEDentro;
	}
	public boolean isEquipamentosComputadores() {
		return equipamentosComputadores;
	}
	public void setEquipamentosComputadores(boolean equipamentosComputadores) {
		this.equipamentosComputadores = equipamentosComputadores;
	}
	public int getComputadoresQuantidade() {
		return computadoresQuantidade;
	}
	public void setComputadoresQuantidade(int computadoresQuantidade) {
		this.computadoresQuantidade = computadoresQuantidade;
	}
	public int getComputadoresQuantidadeAlunos() {
		return computadoresQuantidadeAlunos;
	}
	public void setComputadoresQuantidadeAlunos(int computadoresQuantidadeAlunos) {
		this.computadoresQuantidadeAlunos = computadoresQuantidadeAlunos;
	}
	public boolean isAcessoInternet() {
		return acessoInternet;
	}
	public void setAcessoInternet(boolean acessoInternet) {
		this.acessoInternet = acessoInternet;
	}
	public boolean isBandaLarga() {
		return bandaLarga;
	}
	public void setBandaLarga(boolean bandaLarga) {
		this.bandaLarga = bandaLarga;
	}
	public int getTotalFuncionarios() {
		return totalFuncionarios;
	}
	public void setTotalFuncionarios(int totalFuncionarios) {
		this.totalFuncionarios = totalFuncionarios;
	}
	public boolean isRegularCreche() {
		return regularCreche;
	}
	public void setRegularCreche(boolean regularCreche) {
		this.regularCreche = regularCreche;
	}
	public boolean isRegularPreescolar() {
		return regularPreescolar;
	}
	public void setRegularPreescolar(boolean regularPreescolar) {
		this.regularPreescolar = regularPreescolar;
	}
	public boolean isRegularFundamental() {
		return regularFundamental;
	}
	public void setRegularFundamental(boolean regularFundamental) {
		this.regularFundamental = regularFundamental;
	}
	public boolean isEspecialFundamental() {
		return especialFundamental;
	}
	public void setEspecialFundamental(boolean especialFundamental) {
		this.especialFundamental = especialFundamental;
	}
	public boolean isEspecialEjaFundamental() {
		return especialEjaFundamental;
	}
	public void setEspecialEjaFundamental(boolean especialEjaFundamental) {
		this.especialEjaFundamental = especialEjaFundamental;
	}
	public boolean isEjaFundamental() {
		return ejaFundamental;
	}
	public void setEjaFundamental(boolean ejaFundamental) {
		this.ejaFundamental = ejaFundamental;
	}
	public boolean isEjaProjovem() {
		return ejaProjovem;
	}
	public void setEjaProjovem(boolean ejaProjovem) {
		this.ejaProjovem = ejaProjovem;
	}
	
	@Override
	public String toString(){
		return nome;
	}
}
