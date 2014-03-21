package services;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.joda.time.DateTime;

import models.saude.ContatoVacina;
import models.saude.DosePessoa;

import play.Logger;
import play.Play;
import play.db.jpa.JPA;

public class MailService {

	public void sendMail(String to, String htmlMessage) throws Exception{
		
		String mailhost = Play.application().configuration().getString("mail.host");
		String port = Play.application().configuration().getString("mail.port");
		final String user = Play.application().configuration().getString("mail.user");
		final String pass = Play.application().configuration().getString("mail.pass"); 
				
		Properties props = new Properties();
		//Funcionava Google Versão 1
		/*props.put("mail.smtp.host", mailhost);
		props.put("mail.smtp.socketFactory.port", port);
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", port);*/
		
		//Funciona desktop
		props.put("mail.smtp.host", "smtp.live.com");
		props.put("mail.smtp.socketFactory.port", "587");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.starttls.enable","true");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "587");
		
		// Site ensinando: http://stackoverflow.com/questions/9086420/using-javamail-to-send-from-hotmail?rq=1
		
		Session session = Session.getDefaultInstance(props,
			new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(user,pass);
				}
			});
 
		try {
 
			//Message message = new MimeMessage(session);
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(user,"Vacinação em Dia"));
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

	// Funciona Rio
	public void sendMail2(String to, String htmlMessage) {
		try {
			Logger.info("E-mail: entrou");
			
			Properties props = new Properties();

			String host = "smtp.gmail.com";
			String username = "vacina@cidadaointeligente.com";
			String password = "";
			//String noreply = username;
			//int port = 587;
			int port = 465;
			
			Logger.info("E-mail: setando propriedades");
			
			props.put("mail.transport.protocol", "smtp");
			props.put("mail.smtp.port", port); 
			// Set properties indicating that we want to use STARTTLS to encrypt the connection.
			// The SMTP session will begin on an unencrypted connection, and then the client
			// will issue a STARTTLS command to upgrade to an encrypted connection.
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.starttls.required", "true");
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			// Create a Session object to represent a mail session with the specified properties. 
			Session session = Session.getDefaultInstance(props);
			
			Logger.info("E-mail: criando mensagem");
			
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress("vacina@cidadaointeligente.com","Vacinação em Dia"));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(to));
			message.setSubject("Te ajudamos a cuidar da sua saúde!");
			message.setContent(htmlMessage,"text/html");
			
			Logger.info("E-mail: enviando");
			
			// Create a transport.        
			Transport transport = session.getTransport();

			try {
				Logger.info("E-mail: vai conectar");
				transport.connect(host, port, username, password);	
				Logger.info("E-mail: vai enviar");
				transport.sendMessage(message, message.getAllRecipients());
				
			} catch (MessagingException e) {
				throw new RuntimeException(e);

			}finally{
				Logger.info("E-mail: finally");
				transport.close();
			}

		} catch (AddressException e1) {
			e1.printStackTrace();
		} catch (NoSuchProviderException e1) {
			e1.printStackTrace();
		} catch (MessagingException e1) {
			e1.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		} 
		
		Logger.info("E-mail: terminou");
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
