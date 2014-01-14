package models.saude;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;

import org.joda.time.DateTime;
import org.joda.time.Years;

@Entity
public class ContatoVacina {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column
	private String nome;
	
	@Column
	private String email;
	
	@Column
	private Date nascimento;
	
	@Column(nullable = false)
	private Date createdAt;
	
	public long getId() {
		return id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getNascimento() {
		return nascimento;
	}
	public String getNascimentoFormatted(){
		return new DateTime(nascimento).toString("dd/MM/YYYY");
	}
	public int getIdade(){
		DateTime nasc = new DateTime(nascimento);
		DateTime hoje = new DateTime();
		
		int idade = Years.yearsBetween(nasc, hoje).getYears();
		return idade;
	}
	public void setNascimento(Date nascimento) {
		this.nascimento = nascimento;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public String getCreatedAtFormatted() {
		return new DateTime(createdAt).toString("dd/MM/YYYY");
	}
	@PrePersist
	private void setCreatedAt() {
		this.createdAt = new Date();
	}
}
