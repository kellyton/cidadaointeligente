package models.saude;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Years;

@Entity
public class DosePessoa {
	
	public static final int VENCIDO_MUITO = 0;
	public static final int VENCIDO_POUCO = 1;
	public static final int VENCER_POUCO = 2;
	public static final int VENCER_MUITO = 3;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@ManyToOne
	private Vacina vacina;
	
	@ManyToOne
	private ContatoVacina pessoa;
	
	@Column
	private Date dataPrevista;
	
	@Column(nullable = true)
	private Date dataAplicada;

	@Column(nullable = true)
	private Date dataAlertado;
	
	public long getId() {
		return id;
	}

	public Vacina getVacina() {
		return vacina;
	}

	public void setVacina(Vacina vacina) {
		this.vacina = vacina;
	}

	public ContatoVacina getPessoa() {
		return pessoa;
	}

	public void setPessoa(ContatoVacina pessoa) {
		this.pessoa = pessoa;
	}

	public DateTime getDataPrevista() {
		return new DateTime(dataPrevista);
	}
	
	public String getDataPrevistaFormatted(){
		return new DateTime(dataPrevista).toString("dd/MM/YYYY");
	}

	public void setDataPrevista(DateTime dataPrevista) {
		this.dataPrevista = dataPrevista.toDate();
	}

	public DateTime getDataAplicada() {
		return new DateTime(dataAplicada);
	}
	
	public String getDataAplicadaFormatted(){
		if (dataAplicada == null){
			return null;
		} else {
			return new DateTime(dataAplicada).toString("dd/MM/YYYY");
		}
	}
	public void setDataAplicada(DateTime dataAplicada) {
		this.dataAplicada = dataAplicada.toDate();
	}
	
	public void setDataAlertado(DateTime dataAlertado){
		this.dataAlertado = dataAlertado.toDate();
	}
	public DateTime getDataAlertado() {
		return new DateTime(dataAlertado);
	}
	public boolean foiAlertado(){
		if(dataAlertado == null){
			return false;
		} else {
			return true;
		}
	}
	
	public int getStatus(){
		DateTime vencimento = new DateTime(dataPrevista);
		DateTime hoje = new DateTime();
		
		int baseDistante = 1095; //3 anos
		
		int dias = Days.daysBetween(vencimento, hoje).getDays();
		
		if (dias > baseDistante){//muito longe
			return VENCIDO_MUITO;
		} else if (dias > 0){
			return VENCIDO_POUCO;
		} else if (dias < (-baseDistante)){
			return VENCER_MUITO;
		} else {
			return VENCER_POUCO;
		}
	}
	
	public String getStatusVerbose(){
		switch(getStatus()){
			case VENCIDO_MUITO:
				return "Muito antigo";
			case VENCIDO_POUCO:
				return "Vencido";
			case VENCER_MUITO:
				return "Distante";
			case VENCER_POUCO:
				return "A vencer em breve";
		}
		return "";
	}
	
	public String getStatusColor(){
		switch(getStatus()){
			case VENCIDO_MUITO:
				return "990000";
			case VENCIDO_POUCO:
				return "orange";
			case VENCER_MUITO:
				return "blue";
			case VENCER_POUCO:
				return "green";
		}
		return "";
	}
}
