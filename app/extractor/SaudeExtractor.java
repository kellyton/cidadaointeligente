package extractor;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

import models.Total;
import models.educacao.Escola;
import models.financas.Despesa;
import models.saude.UnidadeSaude;
import play.Logger;
import play.db.jpa.JPA;

public class SaudeExtractor {	
	
	public static final int ESPECIALIDADE_ODONTOLOGICA = 0;
	public static final int FARMACIA_DA_FAMILIA = 1;
	public static final int HOSPITAL = 2 ;
	public static final int POLICLINICA = 3;
	public static final int APOIO_DIAGNOSTICO_TERAPEUTICO = 4; //TODO sem localização. ficou de fora
	public static final int SAUDE_MENTAL = 5;
	public static final int PRONTO_ATENDIMENTO = 6;
	public static final int UNIDADE_BASICA_DE_SAUDE = 7;
	public static final int UNIDADE_SAUDE_DA_FAMILIA = 8;
	public static final int UNIDADE_ESPECIALIZADA = 9;
	
	
	
	public void execute(){
		int results = 0;
		
		Logger.info("Processando Unidades de Saude de 2013");
		
		String arquivo;
		
		arquivo = "./data/saude/ceo.csv";
		results = processarEspecialidade(arquivo, ESPECIALIDADE_ODONTOLOGICA);
		System.out.println("Odontologia processada com " + results + " erros.");
		
		arquivo = "./data/saude/farmacia-da-familia.csv";
		results = processarBasico(arquivo, FARMACIA_DA_FAMILIA);
		System.out.println("Odontologia processada com " + results + " erros.");
		
		arquivo = "./data/saude/hospitais.csv";
		results = processarHospital(arquivo, HOSPITAL);
		System.out.println("Odontologia processada com " + results + " erros.");
		
		arquivo = "./data/saude/policlinicas.csv";
		results = processarEspecialidade2(arquivo, POLICLINICA);
		System.out.println("Odontologia processada com " + results + " erros.");
		
		arquivo = "./data/saude/saudemental.csv";
		results = processarBasico(arquivo, SAUDE_MENTAL);
		System.out.println("Odontologia processada com " + results + " erros.");
		
		arquivo = "./data/saude/servicodeprontoatendimento.csv";
		results = processarEspecialidade2(arquivo, PRONTO_ATENDIMENTO);
		System.out.println("Odontologia processada com " + results + " erros.");
		
		arquivo = "./data/saude/ubs.csv";
		results = processarEspecialidade2(arquivo, UNIDADE_BASICA_DE_SAUDE);
		System.out.println("Odontologia processada com " + results + " erros.");
		
		arquivo = "./data/saude/unidades-especializadas.csv";
		results = processarBasico(arquivo, UNIDADE_ESPECIALIZADA);
		System.out.println("Odontologia processada com " + results + " erros.");
		
		arquivo = "./data/saude/usf.csv";
		results = processarSaudeFamilia(arquivo, UNIDADE_SAUDE_DA_FAMILIA);
		System.out.println("Odontologia processada com " + results + " erros.");
		
		JPA.em().getTransaction().commit();
		JPA.em().getTransaction().begin();

		Logger.info("Saude de 2013 processada");
		
	}
	
	private int processarBasico(String arquivo, int tipo) {
		// TODO Auto-generated method stub
				
		BufferedReader br;
		String line;
		String fields[];
		String csvSplitBy = ";";
		int erros = 0;
		
		UnidadeSaude unidade;

		try {
			
			Logger.info("Processing file: " + arquivo);
			
			if (tipo == UNIDADE_ESPECIALIZADA) {
				br = new BufferedReader(new InputStreamReader(new FileInputStream(arquivo), "Cp1252"));
			} else {
				br = new BufferedReader(new InputStreamReader(new FileInputStream(arquivo), "UTF8"));
			}
			
			//Jump first line
			line = br.readLine();				
			
			while ((line = br.readLine()) != null) {
				unidade = new UnidadeSaude();
				fields = line.split(csvSplitBy, -1); 
				
				unidade.setRpa(Integer.valueOf(fields[0]));
				unidade.setMicro_regiao(Integer.valueOf(fields[1]));
				unidade.setCnes(Long.valueOf(fields[2]));
				unidade.setUnidade(fields[3]);
				unidade.setEndereco(fields[4]);
				unidade.setBairro(fields[5]);
				unidade.setFone(fields[6]);
				unidade.setLatitude(fields[7].replace(",", "."));
				unidade.setLongitude(fields[8].replace(",", "."));
				unidade.setTipo(tipo);
				
				try {
					JPA.em().persist(unidade);					
				} catch (Exception ex){
					Logger.error("Erro salvando unidade " + unidade + ": " + ex.getLocalizedMessage());
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
	
	private int processarEspecialidade(String arquivo, int tipo) {
		// TODO Auto-generated method stub
				
		BufferedReader br;
		String line;
		String fields[];
		String csvSplitBy = ";";
		int erros = 0;
		
		UnidadeSaude unidade;

		try {
			
			Logger.info("Processing file: " + arquivo);
			
			br = new BufferedReader(new InputStreamReader(new FileInputStream(arquivo), "UTF8"));
			
			//Jump first line
			line = br.readLine();				
			
			while ((line = br.readLine()) != null) {
				unidade = new UnidadeSaude();
				fields = line.split(csvSplitBy, -1); 
				
				unidade.setRpa(Integer.valueOf(fields[0]));
				unidade.setMicro_regiao(Integer.valueOf(fields[1]));
				unidade.setCnes(Long.valueOf(fields[2]));
				unidade.setUnidade(fields[3]);
				unidade.setEndereco(fields[4]);
				unidade.setBairro(fields[5]);
				unidade.setEspecialidades(fields[6]);
				unidade.setFone(fields[7]);
				unidade.setLatitude(fields[8].replace(",", "."));
				unidade.setLongitude(fields[9].replace(",", "."));
				unidade.setTipo(tipo);
				
				try {
					JPA.em().persist(unidade);					
				} catch (Exception ex){
					Logger.error("Erro salvando unidade " + unidade + ": " + ex.getLocalizedMessage());
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
	
	/**
	 * Fone e especialidade estão com a ordem trocada
	 * @param arquivo
	 * @param tipo
	 * @return
	 */
	private int processarEspecialidade2(String arquivo, int tipo) {
		// TODO Auto-generated method stub
				
		BufferedReader br;
		String line;
		String fields[];
		String csvSplitBy = ";";
		int erros = 0;
		
		UnidadeSaude unidade;

		try {
			
			Logger.info("Processing file: " + arquivo);
			
			br = new BufferedReader(new InputStreamReader(new FileInputStream(arquivo), "UTF8"));
			
			//Jump first line
			line = br.readLine();				
			
			while ((line = br.readLine()) != null) {
				unidade = new UnidadeSaude();
				fields = line.split(csvSplitBy, -1); 
				
				unidade.setRpa(Integer.valueOf(fields[0]));
				unidade.setMicro_regiao(Integer.valueOf(fields[1]));
				unidade.setCnes(Long.valueOf(fields[2]));
				unidade.setUnidade(fields[3]);
				unidade.setEndereco(fields[4]);
				unidade.setBairro(fields[5]);
				unidade.setFone(fields[6]);
				unidade.setEspecialidades(fields[7]);
				unidade.setLatitude(fields[8].replace(",", "."));
				unidade.setLongitude(fields[9].replace(",", "."));
				unidade.setTipo(tipo);
				
				try {
					JPA.em().persist(unidade);					
				} catch (Exception ex){
					Logger.error("Erro salvando unidade " + unidade + ": " + ex.getLocalizedMessage());
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
	
	private int processarHospital(String arquivo, int tipo) {
		// TODO Auto-generated method stub
				
		BufferedReader br;
		String line;
		String fields[];
		String csvSplitBy = ";";
		int erros = 0;
		
		UnidadeSaude unidade;

		try {
			
			Logger.info("Processing file: " + arquivo);
			
			br = new BufferedReader(new InputStreamReader(new FileInputStream(arquivo), "UTF8"));
			
			//Jump first line
			line = br.readLine();				
			
			while ((line = br.readLine()) != null) {
				unidade = new UnidadeSaude();
				fields = line.split(csvSplitBy, -1); 
			
				unidade.setExtraInfo(fields[0]); //natureza
				unidade.setRpa(Integer.valueOf(fields[1]));
				unidade.setMicro_regiao(Integer.valueOf(fields[2]));
				unidade.setCnes(Long.valueOf(fields[3]));
				unidade.setUnidade(fields[4]);
				unidade.setEndereco(fields[5]);
				unidade.setBairro(fields[6]);
				unidade.setFone(fields[7]);
				unidade.setEspecialidades(fields[8]);
				unidade.setLatitude(fields[9].replace(",", "."));
				unidade.setLongitude(fields[10].replace(",", "."));
				unidade.setTipo(tipo);
				
				try {
					JPA.em().persist(unidade);					
				} catch (Exception ex){
					Logger.error("Erro salvando unidade " + unidade + ": " + ex.getLocalizedMessage());
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
	
	private int processarSaudeFamilia(String arquivo, int tipo) {
		// TODO Auto-generated method stub
				
		BufferedReader br;
		String line;
		String fields[];
		String csvSplitBy = ";";
		int erros = 0;
		
		UnidadeSaude unidade;

		try {
			
			Logger.info("Processing file: " + arquivo);
			
			br = new BufferedReader(new InputStreamReader(new FileInputStream(arquivo), "UTF8"));
			
			//Jump first line
			line = br.readLine();				
			
			while ((line = br.readLine()) != null) {
				unidade = new UnidadeSaude();
				fields = line.split(csvSplitBy, -1); 
				
				unidade.setRpa(Integer.valueOf(fields[0]));
				unidade.setMicro_regiao(Integer.valueOf(fields[1]));
				unidade.setCnes(Long.valueOf(fields[2]));
				unidade.setUnidade(fields[3]);
				unidade.setEndereco(fields[4]);
				unidade.setBairro(fields[5]);
				unidade.setFone(fields[6]);
				
				String extra = "Equipes de saúde da família: " + fields[7] + ", Equipes de saúde bucal: " + fields[8];
				unidade.setExtraInfo(extra);
				
				unidade.setLatitude(fields[9].replace(",", "."));
				unidade.setLongitude(fields[10].replace(",", "."));
				unidade.setTipo(tipo);
				
				try {
					JPA.em().persist(unidade);					
				} catch (Exception ex){
					Logger.error("Erro salvando unidade " + unidade + ": " + ex.getLocalizedMessage());
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
	
	
	private boolean booleanValue(String s){
		if (s.isEmpty()){
			return false;
		} else {
			if (s.trim().equals("1")){
				return true;
			} else {
				return false;
			}
		}
		
	}
	
}
