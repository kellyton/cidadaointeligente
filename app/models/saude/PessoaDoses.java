package models.saude;

import java.util.List;

public class PessoaDoses {
	
	private ContatoVacina pessoa;
	private List<DosePessoa> doses;
	
	public ContatoVacina getPessoa() {
		return pessoa;
	}
	public void setPessoa(ContatoVacina pessoa) {
		this.pessoa = pessoa;
	}
	public List<DosePessoa> getDoses() {
		return doses;
	}
	public void setDoses(List<DosePessoa> doses) {
		this.doses = doses;
	}
	
	

}
