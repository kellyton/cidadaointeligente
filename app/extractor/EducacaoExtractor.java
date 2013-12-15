package extractor;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

import models.Total;
import models.educacao.Escola;
import models.financas.Despesa;
import play.Logger;
import play.db.jpa.JPA;

public class EducacaoExtractor {
	
	private final String ESCOLAS2013 = "./data/edu/escolas2013.csv";
	private final String DOCENTES2013 = "./data/edu/docentes2013.csv";
	private final String ALUNOS2013 = "./data/edu/alunos2013.csv";
	
	public void execute(){
		int results = 0;
		
		
		Logger.info("Processando Educação de 2013");
		
		results = processarEscolas();
		Logger.info("Processamento de escolas finalizado com " + results + " erros.");
		JPA.em().getTransaction().commit();
		JPA.em().getTransaction().begin();
		
		/*results = processarDocentes();
		Logger.info("Processamento de docentes finalizado com " + results + " adições.");		
		JPA.em().getTransaction().commit();
		JPA.em().getTransaction().begin();
		
		//results = processarAlunos();
		Logger.info("Processamento de alunos finalizado com " + results + " adições.");
		JPA.em().getTransaction().commit();
		JPA.em().getTransaction().begin(); */

		Logger.info("Educação de 2013 processada");
		
	}
	
	public int processarEscolas(){
		
		BufferedReader br;
		String line;
		String fields[];
		String csvSplitBy = ";";
		int erros = 0;
		
		Escola escola;
		
		String arquivo = ESCOLAS2013;

		try {
		
			Logger.info("Processing file: " + arquivo);
			
			br = new BufferedReader(new InputStreamReader(new FileInputStream(arquivo), "UTF8"));
			
			//Jump first line
			line = br.readLine();				
			
			int count = 0;
			while ((line = br.readLine()) != null) {
				escola = new Escola();
				fields = line.split(csvSplitBy, -1); 
				
				/*
				Integer.valueOf(fields[0])
				Long.valueOf(fields[0])
				fields[0]
				Boolean.valueOf(fields[0])
				*/
				
				//Auxiliares. Eu poderia pegar exceptions, mas fica mais lento
				int inteiro;
				long longo;
				String temp;
				
				escola.setInepEscola(Long.valueOf(fields[0]));
				escola.setCodigoEscola(Long.valueOf(fields[1]));
				escola.setNome(fields[2]);
				escola.setLatitude(fields[3]);
				escola.setLongitude(fields[4]);
				escola.setCep(fields[5]);
				escola.setEndereco(fields[6]);
				escola.setEnderecoNumero(fields[7]);
				escola.setEnderecoComplemento(fields[8]);
				escola.setBairroCodigo(fields[9]);
				escola.setBairro(fields[10]);
				escola.setTelefone(fields[11]);
				escola.setFax(fields[12]);
				escola.setEmail(fields[13]);
				
				escola.setAguaRedePublica(booleanValue(fields[23]));
				escola.setEsgotoRedePublica(booleanValue(fields[27]));
				escola.setLixoColetaPeriodica(booleanValue(fields[30]));
				
				escola.setDependenciaLaboratorioInformatica(booleanValue(fields[35]));
				escola.setDependenciaLaboratorioCiencias(booleanValue(fields[36]));
				escola.setDependenciaBiblioteca(booleanValue(fields[41]));
				escola.setDependenciaViasAdequadasDeficiencia(booleanValue(fields[49]));
				escola.setDependenciaRefeitorio(booleanValue(fields[51]));
				escola.setDependenciaAuditorio(booleanValue(fields[54]));
				escola.setDependenciaAreaVerde(booleanValue(fields[59]));
				
				escola.setQuantidadeSalasExistentes(Integer.valueOf(fields[61]));
				escola.setQuantidadeSalasUtilizadasForaEDentro(Integer.valueOf(fields[62]));
				
				escola.setEquipamentosComputadores(booleanValue(fields[74]));
				if (escola.isEquipamentosComputadores()) {
					escola.setComputadoresQuantidade(Integer.valueOf(fields[75]));
					escola.setComputadoresQuantidadeAlunos(Integer.valueOf(fields[77]));
				}
				escola.setAcessoInternet(booleanValue(fields[78]));
				escola.setBandaLarga(booleanValue(fields[79]));
				
				temp = fields[80];
				if (!temp.isEmpty()){
					escola.setTotalFuncionarios(Integer.valueOf(temp)); }
				
				escola.setRegularCreche(booleanValue(fields[86]));
				
				escola.setRegularPreescolar(booleanValue(fields[87]));
				
				escola.setRegularFundamental(booleanValue(fields[88]));
				
				escola.setEspecialFundamental(booleanValue(fields[89]));
				
				escola.setEspecialEjaFundamental(booleanValue(fields[90]));
				
				escola.setEjaFundamental(booleanValue(fields[91]));
				
				escola.setEjaProjovem(booleanValue(fields[92]));
				
				try {
					JPA.em().persist(escola);					
				} catch (Exception ex){
					Logger.error("Erro salvando escola " + escola + ": " + ex.getLocalizedMessage());
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
