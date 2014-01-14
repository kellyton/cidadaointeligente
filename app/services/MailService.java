package services;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.joda.time.DateTime;

import models.saude.ContatoVacina;
import models.saude.DosePessoa;
import models.saude.Vacina;

import play.Logger;
import play.db.jpa.JPA;

public class MailService {

	public void sendMail(String to, String htmlMessage) throws Exception{
		
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
 
		Session session = Session.getDefaultInstance(props,
			new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("vacina@cidadaorecifense.com.br","ditaoori");
				}
			});
 
		try {
 
			//Message message = new MimeMessage(session);
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress("vacina@cidadaorecifense.com.br","Vacinação em Dia"));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(to));
			message.setSubject("Te ajudamos a cuidar da sua saúde!");
			
			message.setContent(htmlMessage,"text/html");
 
			Transport.send(message);
			
			Logger.info("Email sent to " + to);
 
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}
		
	}

	public void sendEmailBoasVindas(ContatoVacina user, List<DosePessoa> dosesProximas, List<DosePessoa> dosesUltimas) {
		String corpoPrincipal = null;
		String corpoDosesProximas = null;
		String corpoDosesUltimas = null;
		
		try {
			corpoPrincipal = readFile("./data/emailBoasVindas.html");
		} catch (Exception e){
			Logger.error("Erro lendo arquivo de email de boas vindas para envio para " + user.getEmail() + 
					". Erro: " + e.getMessage(), e);
			return;
		}
		
		try {
			corpoDosesProximas = readFile("./data/detalheDoses.html");
			corpoDosesUltimas = corpoDosesProximas;
		} catch (Exception e){
			Logger.error("Erro lendo arquivo de email de detalhe de doses para " + user.getEmail() +
					". Erro: " + e.getMessage(), e);
			return;
		}
	
		for (int i = 0; i < 2; i++){
			corpoDosesProximas = corpoDosesProximas.replace("#vacina" + i, dosesProximas.get(i).getVacina().getVacina());
			corpoDosesProximas = corpoDosesProximas.replace("#protecao" + i, dosesProximas.get(i).getVacina().getDoencaProtecao());
			corpoDosesProximas = corpoDosesProximas.replace("#dataPrevista" + i, dosesProximas.get(i).getDataPrevistaFormatted());
		}
		
		for (int i = 0; i < 2; i++){
			corpoDosesUltimas = corpoDosesUltimas.replace("#vacina" + i, dosesUltimas.get(i).getVacina().getVacina());
			corpoDosesUltimas = corpoDosesUltimas.replace("#protecao" + i, dosesUltimas.get(i).getVacina().getDoencaProtecao());
			corpoDosesUltimas = corpoDosesUltimas.replace("#dataPrevista" + i, dosesUltimas.get(i).getDataPrevistaFormatted());
		}
		
		corpoPrincipal = corpoPrincipal.replace("#pessoa", user.getNome());
		corpoPrincipal = corpoPrincipal.replace("#link", "http://www.cidadaorecifense.com/saude/vacinacao/"+user.getId());
		corpoPrincipal = corpoPrincipal.replace("#proximas", corpoDosesProximas);
		corpoPrincipal = corpoPrincipal.replace("#ultimas", corpoDosesUltimas);
		
		try {
			sendMail(user.getEmail(), corpoPrincipal);
		} catch (Exception e) {
			Logger.error("Erro enviando o e-mail para " + user.getEmail() + ". Erro: " + e.getMessage(), e);
			e.printStackTrace();
		}

	}

	public void sendEmailAviso(List<DosePessoa> doses) {
		String corpoPrincipalOriginal = null;
		String corpoPrincipal;
		String corpoDoseOriginal = null;
		String corpoDose;
		
		try {
			corpoPrincipalOriginal = readFile("./data/emailAviso.html");
		} catch (Exception e){
			Logger.error("Erro lendo arquivo de email de aviso para envio em lote." +  
					" Erro: " + e.getMessage(), e);
			return;
		}
		
		try {
			corpoDoseOriginal = readFile("./data/detalheDoseUnica.html");
		} catch (Exception e){
			Logger.error("Erro lendo arquivo de email de detalhe de doses para envio em lote." + 
					" Erro: " + e.getMessage(), e);
			return;
		}
		
		for (DosePessoa dose: doses){
			Logger.info("Enviando e-mail para " + dose.getPessoa().getEmail() + " - " + dose.getVacina() + "." );
			
			corpoPrincipal = corpoPrincipalOriginal;
			corpoDose = corpoDoseOriginal;
			
			corpoDose = corpoDose.replace("#vacina0", dose.getVacina().getVacina());
			corpoDose = corpoDose.replace("#protecao0", dose.getVacina().getDoencaProtecao());
			corpoDose = corpoDose.replace("#dataPrevista0", dose.getDataPrevistaFormatted());
			
			corpoPrincipal = corpoPrincipal.replace("#pessoa", dose.getPessoa().getNome());
			corpoPrincipal = corpoPrincipal.replace("#link", "http://rio.cidadaointeligente.com/saude/vacinacao/"+dose.getPessoa().getId());
			corpoPrincipal = corpoPrincipal.replace("#proximas", corpoDose);
			
			try {
				sendMail(dose.getPessoa().getEmail(), corpoPrincipal);
				dose.setDataAlertado(new DateTime());
				JPA.em().persist(dose);
			} catch (Exception e) {
				Logger.error("Erro enviando o e-mail para " + dose.getPessoa().getEmail() + " - " + dose.getVacina() + ". Erro: " + e.getMessage(), e);
			}
		}
		
	}
	
	private String readFile(String file) throws Exception {
		BufferedReader br = null;
		String line;
		StringBuffer buffer = new StringBuffer();
		
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			
			while ((line = br.readLine()) != null) {
				buffer.append(line);
			}
			return buffer.toString();
		} catch (Exception e){
			Logger.error("Erro lendo arquivo " + file);
			throw new Exception(e);
		} finally {
			if (br != null) br.close();
		}
	}

	
}
