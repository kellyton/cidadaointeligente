package extractor;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

import models.Total;
import models.cultura.Cultura;
import models.financas.Despesa;
import play.Logger;
import play.db.jpa.JPA;
import static models.cultura.Cultura.*;

public class CulturaExtractor {	
	
	public void execute(){
		int results = 0;
		
		Logger.info("Processando Equipamentos Culturais");
		
		String arquivo;
		
		arquivo = "./data/cult/mercadospublicos.csv";
		results = processarMercadoPonte(arquivo, MERCADO_PUBLICO);
		System.out.println("Processada com " + results + " erros.");
		
		arquivo = "./data/cult/feiraslivres.csv";
		results = processarFeira(arquivo, FEIRA_LIVRE);
		System.out.println("Processada com " + results + " erros.");
		
		arquivo = "./data/cult/shopping.csv";
		results = processarShopping(arquivo, SHOPPING);
		System.out.println("Processada com " + results + " erros.");
		
		arquivo = "./data/cult/teatros.csv";
		results = processarTeatro(arquivo, TEATRO);
		System.out.println("Processada com " + results + " erros.");
		
		arquivo = "./data/cult/museus.csv";
		results = processarMuseu(arquivo, MUSEU);
		System.out.println("Processada com " + results + " erros.");
		
		arquivo = "./data/cult/pontesdorecife.csv";
		results = processarMercadoPonte(arquivo, PONTE);
		System.out.println("Processada com " + results + " erros.");
		
		JPA.em().getTransaction().commit();
		JPA.em().getTransaction().begin();

		Logger.info("Cultura de 2013 processada");
		
	}
	
	private int processarMercadoPonte(String arquivo, int tipo) {
		BufferedReader br;
		String line;
		String fields[];
		String csvSplitBy = ";";
		int erros = 0;
		
		Cultura equip;

		try {
			
			Logger.info("Processing file: " + arquivo);
			br = new BufferedReader(new InputStreamReader(new FileInputStream(arquivo), "UTF8"));
			line = br.readLine();//pula o cabeçalho
			while ((line = br.readLine()) != null) {
				equip = new Cultura();
				fields = line.split(csvSplitBy, -1); 
				
				equip.setNome(fields[0]);
				equip.setDescricao(fields[1]);
				equip.setBairro(fields[2]);
				equip.setLatitude(fields[3].replace(",", "."));
				equip.setLongitude(fields[4].replace(",", "."));
				equip.setTipo(tipo);
								
				try {
					JPA.em().persist(equip);					
				} catch (Exception ex){
					Logger.error("Erro salvando equipamento cultural " + equip.getNome() + ": " + ex.getLocalizedMessage());
					erros++;
				}
			}
			Logger.info("Success processing " + arquivo);
			br.close();
		} catch (Exception e){
			Logger.error("Erro processando arquivo " + arquivo + ": " + e.getLocalizedMessage());
			e.printStackTrace();
			erros++;
		}
		return erros;
	}
	
	private int processarFeira(String arquivo, int tipo) {
		BufferedReader br;
		String line;
		String fields[];
		String csvSplitBy = ";";
		int erros = 0;
		
		Cultura equip;

		try {
			
			Logger.info("Processing file: " + arquivo);
			br = new BufferedReader(new InputStreamReader(new FileInputStream(arquivo), "UTF8"));
			line = br.readLine();//pula o cabeçalho
			while ((line = br.readLine()) != null) {
				equip = new Cultura();
				fields = line.split(csvSplitBy, -1); 
				
				equip.setNome(fields[0]);
				equip.setEndereco(fields[1]);
				equip.setLatitude(fields[5].replace(",", "."));
				equip.setLongitude(fields[6].replace(",", "."));
				equip.setDescricao(fields[2] + ", " + fields[3]);
				equip.setExtraInfo(fields[4]);
				equip.setTipo(tipo);
								
				try {
					JPA.em().persist(equip);					
				} catch (Exception ex){
					Logger.error("Erro salvando equipamento cultural " + equip.getNome() + ": " + ex.getLocalizedMessage());
					erros++;
				}
			}
			Logger.info("Success processing " + arquivo);
			br.close();
		} catch (Exception e){
			Logger.error("Erro processando arquivo " + arquivo + ": " + e.getLocalizedMessage());
			e.printStackTrace();
			erros++;
		}
		return erros;
	}
	
	private int processarShopping(String arquivo, int tipo) {
		BufferedReader br;
		String line;
		String fields[];
		String csvSplitBy = ";";
		int erros = 0;
		
		Cultura equip;

		try {
			
			Logger.info("Processing file: " + arquivo);
			br = new BufferedReader(new InputStreamReader(new FileInputStream(arquivo), "UTF8"));
			line = br.readLine();//pula o cabeçalho
			while ((line = br.readLine()) != null) {
				equip = new Cultura();
				fields = line.split(csvSplitBy, -1); 
				
				equip.setNome(fields[0]);
				equip.setDescricao(fields[1]);
				equip.setBairro(fields[2]);
				equip.setEndereco(fields[3]);
				equip.setLatitude(fields[4].replace(",", "."));
				equip.setLongitude(fields[5].replace(",", "."));
				equip.setExtraInfo(
						"Telefone: " + fields[6] + 
						", Site: "  + fields[7] +
						", Horário: " + fields[8] +
						", aos domingos: "  + fields[9]
						);
				equip.setTipo(tipo);
								
				try {
					JPA.em().persist(equip);					
				} catch (Exception ex){
					Logger.error("Erro salvando equipamento cultural " + equip.getNome() + ": " + ex.getLocalizedMessage());
					erros++;
				}
			}
			Logger.info("Success processing " + arquivo);
			br.close();
		} catch (Exception e){
			Logger.error("Erro processando arquivo " + arquivo + ": " + e.getLocalizedMessage());
			e.printStackTrace();
			erros++;
		}
		return erros;
	}
	
	private int processarTeatro(String arquivo, int tipo) {
		BufferedReader br;
		String line;
		String fields[];
		String csvSplitBy = ";";
		int erros = 0;
		
		Cultura equip;

		try {
			
			Logger.info("Processing file: " + arquivo);
			br = new BufferedReader(new InputStreamReader(new FileInputStream(arquivo), "UTF8"));
			line = br.readLine();//pula o cabeçalho
			while ((line = br.readLine()) != null) {
				equip = new Cultura();
				fields = line.split(csvSplitBy, -1); 
				
				equip.setNome(fields[0]);
				equip.setDescricao(fields[1]);
				equip.setBairro(fields[2]);
				equip.setEndereco(fields[3]);
				equip.setFone(fields[4]);
				equip.setLatitude(fields[5].replace(",", "."));
				equip.setLongitude(fields[6].replace(",", "."));
				equip.setTipo(tipo);
								
				try {
					JPA.em().persist(equip);					
				} catch (Exception ex){
					Logger.error("Erro salvando equipamento cultural " + equip.getNome() + ": " + ex.getLocalizedMessage());
					erros++;
				}
			}
			Logger.info("Success processing " + arquivo);
			br.close();
		} catch (Exception e){
			Logger.error("Erro processando arquivo " + arquivo + ": " + e.getLocalizedMessage());
			e.printStackTrace();
			erros++;
		}
		return erros;
	}
	
	private int processarMuseu(String arquivo, int tipo) {
		BufferedReader br;
		String line;
		String fields[];
		String csvSplitBy = ";";
		int erros = 0;
		
		Cultura equip;

		try {
			
			Logger.info("Processing file: " + arquivo);
			br = new BufferedReader(new InputStreamReader(new FileInputStream(arquivo), "UTF8"));
			line = br.readLine();//pula o cabeçalho
			while ((line = br.readLine()) != null) {
				equip = new Cultura();
				fields = line.split(csvSplitBy, -1); 
				
				equip.setNome(fields[0]);
				equip.setDescricao(fields[1]);
				equip.setBairro(fields[2]);
				equip.setEndereco(fields[3]);
				equip.setLatitude(fields[4].replace(",", "."));
				equip.setLongitude(fields[5].replace(",", "."));
				equip.setFone(fields[6]);
				equip.setExtraInfo("Site: " + fields[7]);
				equip.setTipo(tipo);
								
				try {
					JPA.em().persist(equip);					
				} catch (Exception ex){
					Logger.error("Erro salvando equipamento cultural " + equip.getNome() + ": " + ex.getLocalizedMessage());
					erros++;
				}
			}
			Logger.info("Success processing " + arquivo);
			br.close();
		} catch (Exception e){
			Logger.error("Erro processando arquivo " + arquivo + ": " + e.getLocalizedMessage());
			e.printStackTrace();
			erros++;
		}
		return erros;
	}
	
}
