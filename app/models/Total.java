package models;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Total implements Comparable<Total>{
	
	public static final int DESPESA_TOTAL = 0;
	public static final int DESPESA_POR_CREDOR = 1;
	public static final int DESPESA_POR_FUNCAO = 2;
	public static final int DESPESA_POR_ELEMENTO = 3;
	public static final int DESPESA_POR_ORGAO = 4;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column
	private int tipo;
	
	@Column
	private String nome;
	
	@Column
	private int ano;
	
	@Column
	private Double valor;
	
	@Column
	private double valor2;
	
	@Column
	private String extraInfo;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public String getNome() {
		return nome;
	}
	
	public String getNomeCurto() {
		if (nome.length() > 20) {
			return nome.substring(0,20);
		} else {
			return nome;
		}
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public Double getValor() {
		return valor;
	}
	
	public String getValorFormatado() {
		NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt_BR"));
		DecimalFormat df = (DecimalFormat)nf;
		
		return new DecimalFormat("#,###.00").format(valor);
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public double getValor2() {
		return valor2;
	}
	
	public String getValor2Formatado() {
		NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt_BR"));
		DecimalFormat df = (DecimalFormat)nf;
		
		return new DecimalFormat("#,###.00").format(valor2);
	}

	public void setValor2(double valor2) {
		this.valor2 = valor2;
	}

	public String getExtraInfo() {
		return extraInfo;
	}

	public void setExtraInfo(String extraInfo) {
		this.extraInfo = extraInfo;
	}
	
	@Override
	public String toString() {
		return nome + " - " + ano + " - " + valor;
	}

	@Override
	public int compareTo(Total o) {
		if (this.valor > o.valor){
			return 1;
		} else if (this.valor < o.valor){
			return -1;
		} else { //iguais
			if (this.valor2 > o.valor2){
				return 1;
			} else if (this.valor2 < o.valor2){
				return -1;
			} else {
				return 0;
			}
		}
	}
	


}
