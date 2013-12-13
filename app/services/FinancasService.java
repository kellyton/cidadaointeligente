package services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.LinkedList;

import models.origin.financas.Despesa;
import play.Logger;
import play.db.jpa.JPA;
import play.mvc.Result;

public class FinancasService {

	public void processarDespesas(){
		
		String [] filenames = {
				"./data/despesas-2002.csv",
				"./data/despesas-2003.csv",
				"./data/despesas-2004.csv",
				"./data/despesas-2005.csv", 
				"./data/despesas-2006.csv",
				"./data/despesas-2007.csv",
				"./data/despesas-2008.csv",
				"./data/despesas-2009.csv",
				"./data/despesas-2010.csv",
				"./data/despesas-2011.csv",
				"./data/despesas-2012.csv",
				"./data/recife-dados-despesas-2013.csv"
		};
		
		BufferedReader br;
		String line;
		String fields[];
		String csvSplitBy = "\";\"";
		String d;
		
		LinkedList<Despesa> despesas;
		Despesa despesa;
		
		for (int i = 0; i < filenames.length; i++){

			try {
			
				Logger.info("Processing file: " + filenames[i]);
				
				br = new BufferedReader(new FileReader(filenames[i]));
				
				//Jump first line
				line = br.readLine();
				while ((line = br.readLine()) != null) {
					despesa = new Despesa();
					fields = line.split(csvSplitBy); 
					
					despesa.setAnoMovimentacao(Integer.valueOf(fields[0]));
					despesa.setMesMovimentacao(Integer.valueOf(fields[1]));
					despesa.setOrgaoCodigo(Integer.valueOf(fields[2]));
					despesa.setOrgaoNome(fields[3]);
					despesa.setUnidadeCodigo(fields[4]);
					despesa.setUnidadeNome(fields[5]);
					despesa.setCategoriaEconomicaCodigo(Integer.valueOf(fields[6]));
					despesa.setCategoriaEconomicaNome(fields[7]);
					despesa.setGrupoDespesaCodigo(Integer.valueOf(fields[8]));
					despesa.setGrupoDespesaNome(fields[9]);
					despesa.setModalidadeAplicacaoCodigo(Integer.valueOf(fields[10]));
					despesa.setModalidadeAplicacaoNome(fields[11]);
					despesa.setElementoCodigo(Integer.valueOf(fields[12]));
					despesa.setElementoNome(fields[13]);
					despesa.setSubelementoCodigo(Integer.valueOf(fields[14]));
					despesa.setSubelementoNome(fields[15]);
					despesa.setFuncaoCodigo(Integer.valueOf(fields[16]));
					despesa.setFuncaoNome(fields[17]);
					despesa.setSubfuncaoCodigo(Integer.valueOf(fields[18]));
					despesa.setSubfuncaoNome(fields[19]);
					despesa.setProgramaCodigo(Integer.valueOf(fields[20]));
					despesa.setProgramaNome(fields[21]);
					despesa.setAcaoCodigo(Integer.valueOf(fields[22]));
					despesa.setAcaoNome(fields[23]);
					despesa.setFonteRecursoCodigo(Integer.valueOf(fields[24]));
					despesa.setFonteRecursoNome(fields[25]);
					despesa.setEmpenhoAno(Integer.valueOf(fields[26]));
					despesa.setEmpenhoModalidadeNome(fields[27]);
					despesa.setEmpenhoModalidadeCodigo(Integer.valueOf(fields[28]));
					despesa.setEmpenhoNumero(Integer.valueOf(fields[27]));
					despesa.setSubempenho(Integer.valueOf(fields[28]));
					despesa.setIndicadorSubempenho(fields[29]);
					despesa.setCredorCodigo(Integer.valueOf(fields[30]));
					despesa.setCredorNome(fields[31]);
					despesa.setModalidadeLicitacaoCodigo(Integer.valueOf(fields[32]));
					despesa.setModalidadeLicitacaoNome(fields[33]);
					despesa.setValorEmpenhado(Double.valueOf(fields[34]));
					despesa.setValorLiquidado(Double.valueOf(fields[35]));
					despesa.setValorPago(Double.valueOf(fields[36]));
					
					JPA.em().persist(despesa);
				}
				
				Logger.info("Success processing " + filenames[i]);
				
			} catch (Exception e){
				Logger.error("Erro processando arquivo " + filenames[i] + ": " + e.getLocalizedMessage());
			}
		}
	}
	
}
