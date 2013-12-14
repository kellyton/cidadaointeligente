package models.origin.financas;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Despesa{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column
	private int anoMovimentacao;
	@Column
	private int mesMovimentacao;
	@Column
	private int orgaoCodigo;
	@Column
	private String orgaoNome;
	@Column
	private String unidadeCodigo;
	@Column
	private String unidadeNome;
	@Column
	private int categoriaEconomicaCodigo;
	@Column
	private String categoriaEconomicaNome;
	@Column
	private int grupoDespesaCodigo;
	@Column
	private String grupoDespesaNome;
	@Column
	private int modalidadeAplicacaoCodigo;
	@Column
	private String modalidadeAplicacaoNome;
	@Column
	private int elementoCodigo;
	@Column
	private String elementoNome;
	@Column
	private int subelementoCodigo;
	@Column
	private String subelementoNome;
	@Column
	private int funcaoCodigo;
	@Column
	private String funcaoNome;
	@Column
	private int subfuncaoCodigo;
	@Column
	private String subfuncaoNome;
	@Column
	private int programaCodigo;
	@Column
	private String programaNome;
	@Column
	private int acaoCodigo;
	@Column
	private String acaoNome;
	@Column
	private int fonteRecursoCodigo;
	@Column
	private String fonteRecursoNome;
	@Column
	private int empenhoAno;
	@Column
	private String empenhoModalidadeNome;
	@Column
	private int empenhoModalidadeCodigo;
	@Column
	private int empenhoNumero;
	@Column
	private int subempenho;
	@Column
	private String indicadorSubempenho;
	@Column
	private int credorCodigo;
	@Column
	private String credorNome;
	@Column
	private int modalidadeLicitacaoCodigo;
	@Column
	private String modalidadeLicitacaoNome;
	@Column
	private double valorEmpenhado;
	@Column
	private double valorLiquidado;
	@Column
	private double valorPago;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getAnoMovimentacao() {
		return anoMovimentacao;
	}
	public void setAnoMovimentacao(int anoMovimentacao) {
		this.anoMovimentacao = anoMovimentacao;
	}
	public int getMesMovimentacao() {
		return mesMovimentacao;
	}
	public void setMesMovimentacao(int mesMovimentacao) {
		this.mesMovimentacao = mesMovimentacao;
	}
	public int getOrgaoCodigo() {
		return orgaoCodigo;
	}
	public void setOrgaoCodigo(int orgaoCodigo) {
		this.orgaoCodigo = orgaoCodigo;
	}
	public String getOrgaoNome() {
		return orgaoNome;
	}
	public void setOrgaoNome(String orgaoNome) {
		this.orgaoNome = orgaoNome;
	}
	public String getUnidadeCodigo() {
		return unidadeCodigo;
	}
	public void setUnidadeCodigo(String unidadeCodigo) {
		this.unidadeCodigo = unidadeCodigo;
	}
	public String getUnidadeNome() {
		return unidadeNome;
	}
	public void setUnidadeNome(String unidadeNome) {
		this.unidadeNome = unidadeNome;
	}
	public int getCategoriaEconomicaCodigo() {
		return categoriaEconomicaCodigo;
	}
	public void setCategoriaEconomicaCodigo(int categoriaEconomicaCodigo) {
		this.categoriaEconomicaCodigo = categoriaEconomicaCodigo;
	}
	public String getCategoriaEconomicaNome() {
		return categoriaEconomicaNome;
	}
	public void setCategoriaEconomicaNome(String categoriaEconomicaNome) {
		this.categoriaEconomicaNome = categoriaEconomicaNome;
	}
	public int getGrupoDespesaCodigo() {
		return grupoDespesaCodigo;
	}
	public void setGrupoDespesaCodigo(int grupoDespesaCodigo) {
		this.grupoDespesaCodigo = grupoDespesaCodigo;
	}
	public String getGrupoDespesaNome() {
		return grupoDespesaNome;
	}
	public void setGrupoDespesaNome(String grupoDespesaNome) {
		this.grupoDespesaNome = grupoDespesaNome;
	}
	public int getModalidadeAplicacaoCodigo() {
		return modalidadeAplicacaoCodigo;
	}
	public void setModalidadeAplicacaoCodigo(int modalidadeAplicacaoCodigo) {
		this.modalidadeAplicacaoCodigo = modalidadeAplicacaoCodigo;
	}
	public String getModalidadeAplicacaoNome() {
		return modalidadeAplicacaoNome;
	}
	public void setModalidadeAplicacaoNome(String modalidadeAplicacaoNome) {
		this.modalidadeAplicacaoNome = modalidadeAplicacaoNome;
	}
	public int getElementoCodigo() {
		return elementoCodigo;
	}
	public void setElementoCodigo(int elementoCodigo) {
		this.elementoCodigo = elementoCodigo;
	}
	public String getElementoNome() {
		return elementoNome;
	}
	public void setElementoNome(String elementoNome) {
		this.elementoNome = elementoNome;
	}
	public int getSubelementoCodigo() {
		return subelementoCodigo;
	}
	public void setSubelementoCodigo(int subelementoCodigo) {
		this.subelementoCodigo = subelementoCodigo;
	}
	public String getSubelementoNome() {
		return subelementoNome;
	}
	public void setSubelementoNome(String subelementoNome) {
		this.subelementoNome = subelementoNome;
	}
	public int getFuncaoCodigo() {
		return funcaoCodigo;
	}
	public void setFuncaoCodigo(int funcaoCodigo) {
		this.funcaoCodigo = funcaoCodigo;
	}
	public String getFuncaoNome() {
		return funcaoNome;
	}
	public void setFuncaoNome(String funcaoNome) {
		this.funcaoNome = funcaoNome;
	}
	public int getSubfuncaoCodigo() {
		return subfuncaoCodigo;
	}
	public void setSubfuncaoCodigo(int subfuncaoCodigo) {
		this.subfuncaoCodigo = subfuncaoCodigo;
	}
	public String getSubfuncaoNome() {
		return subfuncaoNome;
	}
	public void setSubfuncaoNome(String subfuncaoNome) {
		this.subfuncaoNome = subfuncaoNome;
	}
	public int getProgramaCodigo() {
		return programaCodigo;
	}
	public void setProgramaCodigo(int programaCodigo) {
		this.programaCodigo = programaCodigo;
	}
	public String getProgramaNome() {
		return programaNome;
	}
	public void setProgramaNome(String programaNome) {
		this.programaNome = programaNome;
	}
	public int getAcaoCodigo() {
		return acaoCodigo;
	}
	public void setAcaoCodigo(int acaoCodigo) {
		this.acaoCodigo = acaoCodigo;
	}
	public String getAcaoNome() {
		return acaoNome;
	}
	public void setAcaoNome(String acaoNome) {
		this.acaoNome = acaoNome;
	}
	public int getFonteRecursoCodigo() {
		return fonteRecursoCodigo;
	}
	public void setFonteRecursoCodigo(int fonteRecursoCodigo) {
		this.fonteRecursoCodigo = fonteRecursoCodigo;
	}
	public String getFonteRecursoNome() {
		return fonteRecursoNome;
	}
	public void setFonteRecursoNome(String fonteRecursoNome) {
		this.fonteRecursoNome = fonteRecursoNome;
	}
	public int getEmpenhoAno() {
		return empenhoAno;
	}
	public void setEmpenhoAno(int empenhoAno) {
		this.empenhoAno = empenhoAno;
	}
	public String getEmpenhoModalidadeNome() {
		return empenhoModalidadeNome;
	}
	public void setEmpenhoModalidadeNome(String empenhoModalidadeNome) {
		this.empenhoModalidadeNome = empenhoModalidadeNome;
	}
	public int getEmpenhoModalidadeCodigo() {
		return empenhoModalidadeCodigo;
	}
	public void setEmpenhoModalidadeCodigo(int empenhoModalidadeCodigo) {
		this.empenhoModalidadeCodigo = empenhoModalidadeCodigo;
	}
	public int getEmpenhoNumero() {
		return empenhoNumero;
	}
	public void setEmpenhoNumero(int empenhoNumero) {
		this.empenhoNumero = empenhoNumero;
	}
	public int getSubempenho() {
		return subempenho;
	}
	public void setSubempenho(int subempenho) {
		this.subempenho = subempenho;
	}
	public String getIndicadorSubempenho() {
		return indicadorSubempenho;
	}
	public void setIndicadorSubempenho(String indicadorSubempenho) {
		this.indicadorSubempenho = indicadorSubempenho;
	}
	public int getCredorCodigo() {
		return credorCodigo;
	}
	public void setCredorCodigo(int credorCodigo) {
		this.credorCodigo = credorCodigo;
	}
	public String getCredorNome() {
		return credorNome;
	}
	public void setCredorNome(String credorNome) {
		this.credorNome = credorNome;
	}
	public int getModalidadeLicitacaoCodigo() {
		return modalidadeLicitacaoCodigo;
	}
	public void setModalidadeLicitacaoCodigo(int modalidadeLicitacaoCodigo) {
		this.modalidadeLicitacaoCodigo = modalidadeLicitacaoCodigo;
	}
	public String getModalidadeLicitacaoNome() {
		return modalidadeLicitacaoNome;
	}
	public void setModalidadeLicitacaoNome(String modalidadeLicitacaoNome) {
		this.modalidadeLicitacaoNome = modalidadeLicitacaoNome;
	}
	public double getValorEmpenhado() {
		return valorEmpenhado;
	}
	public void setValorEmpenhado(double valorEmpenhado) {
		this.valorEmpenhado = valorEmpenhado;
	}
	public double getValorLiquidado() {
		return valorLiquidado;
	}
	public void setValorLiquidado(double valorLiquidado) {
		this.valorLiquidado = valorLiquidado;
	}
	public double getValorPago() {
		return valorPago;
	}
	public void setValorPago(double valorPago) {
		this.valorPago = valorPago;
	}

	
}
