package extractor;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

import models.Total;
import models.financas.Despesa;
import play.Logger;
import play.db.jpa.JPA;

public class FinancasExtractor {
	
	HashMap <Integer, String> arquivos = new HashMap<Integer, String>();
	
	{
		arquivos.put(2002, "./data/despesas-2002.csv");
		arquivos.put(2003, "./data/despesas-2003.csv");
		arquivos.put(2004, "./data/despesas-2004.csv");
		arquivos.put(2005, "./data/despesas-2005.csv");
		arquivos.put(2006, "./data/despesas-2006.csv");
		arquivos.put(2007, "./data/despesas-2007.csv");
		arquivos.put(2008, "./data/despesas-2008.csv");
		arquivos.put(2009, "./data/despesas-2009.csv");
		arquivos.put(2010, "./data/despesas-2010.csv");
		arquivos.put(2011, "./data/despesas-2011.csv");
		arquivos.put(2012, "./data/despesas-2012.csv");
		arquivos.put(2013, "./data/despesas-2013.csv");
	}
	
	public void execute(){
		int results = 0;
		
		
		for (int ano = 2002; ano <= 2013; ano++){
			System.gc();
			Logger.info("Processando ano de " + ano);
			
			results = processarArquivo(ano);
			Logger.info("Arquivos processados com " + results + " erros.");
		
			//Necessário senão não tem dados para totalizar
			JPA.em().getTransaction().commit();
			JPA.em().getTransaction().begin();
			
			results = totalizarTotal(ano);
			Logger.info("Totalização total finalizada com " + results + " adições.");
			results = totalizarEmpresas(ano);
			Logger.info("Totalização por empresa finalizada com " + results + " adições.");
			results = totalizarFuncao(ano);
			Logger.info("Totalização por função finalizada com " + results + " adições.");
			results = totalizarElemento(ano);
			Logger.info("Totalização por elemento finalizada com " + results + " adições.");
			results = totalizarOrgao(ano);
			Logger.info("Totalização por órgão finalizada com " + results + " adições.");
			
			JPA.em().getTransaction().commit();
			JPA.em().getTransaction().begin();
			Logger.info("Ano de " + ano + " processado.");
		}
		
	}
	
	/**
	 * Calcula ogasto total do ano
	 */
	private int totalizarTotal(int ano) {
		String query = "INSERT INTO total(`tipo`, `nome`, `ano`, `valor`, `valor2`, `extraInfo`) " +
						"SELECT :tipo, 'Total de Despesas', anoMovimentacao, sum(valorEmpenhado), sum(valorPago), null " +
						"FROM despesa WHERE anoMovimentacao = :ano";
		
		int results = JPA.em().createNativeQuery(query)
			.setParameter("tipo", Total.DESPESA_TOTAL)
			.setParameter("ano", ano)
			.executeUpdate();
		
		return results;
	}
	
	/**
	 * Calcula os totais das empresas e joga na tabela de totais
	 */
	private int totalizarEmpresas(int ano) {
		String query = "INSERT INTO total(`tipo`, `nome`, `ano`, `valor`, `valor2`, `extraInfo`) " +
						"SELECT :tipo, credorNome, anoMovimentacao, sum(valorEmpenhado), sum(valorPago), null " +
						"FROM despesa WHERE anoMovimentacao = :ano GROUP BY credorNome";
		
		int results = JPA.em().createNativeQuery(query)
			.setParameter("tipo", Total.DESPESA_POR_CREDOR)
			.setParameter("ano", ano)
			.executeUpdate();
		
		return results;
	}

	/**
	 * Calcula os totais por função e joga na tabela de totais
	 */
	private int totalizarFuncao(int ano) {
		String query = "INSERT INTO total(`tipo`, `nome`, `ano`, `valor`, `valor2`, `extraInfo`) " +
				"SELECT :tipo, funcaoNome, anoMovimentacao, sum(valorEmpenhado), sum(valorPago), null " +
				"FROM despesa WHERE anoMovimentacao = :ano GROUP BY funcaoNome";
		int results = JPA.em().createNativeQuery(query)
			.setParameter("tipo", Total.DESPESA_POR_FUNCAO)
			.setParameter("ano", ano)
			.executeUpdate();
		
		return results;
	}
	
	/**
	 * Calcula os totais por ELEMENTO e joga na tabela de totais
	 */
	private int totalizarElemento(int ano) {
		String query = "INSERT INTO total(`tipo`, `nome`, `ano`, `valor`, `valor2`, `extraInfo`) " +
				"SELECT :tipo, elementoNome, anoMovimentacao, sum(valorEmpenhado), sum(valorPago), null " +
				"FROM despesa WHERE anoMovimentacao = :ano GROUP BY elementoNome";
		int results = JPA.em().createNativeQuery(query)
			.setParameter("tipo", Total.DESPESA_POR_ELEMENTO)
			.setParameter("ano", ano)
			.executeUpdate();
		
		return results;
	}
	
	/**
	 * Calcula os totais por ORGAO e joga na tabela de totais
	 */
	private int totalizarOrgao(int ano) {
		String query = "INSERT INTO total(`tipo`, `nome`, `ano`, `valor`, `valor2`, `extraInfo`) " +
				"SELECT :tipo, orgaoNome, anoMovimentacao, sum(valorEmpenhado), sum(valorPago), null " +
				"FROM despesa WHERE anoMovimentacao = :ano GROUP BY orgaoNome";
		int results = JPA.em().createNativeQuery(query)
			.setParameter("tipo", Total.DESPESA_POR_ORGAO)
			.setParameter("ano", ano)
			.executeUpdate();
		
		return results;
	}
	
	public int processarArquivo(int ano){
		
		int erros = 0;
		
		/*String [] filenames = {
				/*"./data/despesas-2002.csv",
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
		};*/
		
		BufferedReader br;
		String line;
		String fields[];
		String csvSplitBy = ";";
		
		Despesa despesa;
		
		String arquivo = arquivos.get(ano);
		
		//for (int i = 0; i < filenames.length; i++){

			try {
			
				Logger.info("Processing file: " + arquivo);
				
				if (ano == 2013){
					br = new BufferedReader(new InputStreamReader(new FileInputStream(arquivo), "Cp1252"));
				} else {
					br = new BufferedReader(new InputStreamReader(new FileInputStream(arquivo), "UTF8"));
				}
				
				System.gc();
				
				//Jump first line
				line = br.readLine();				
				
				int count = 0;
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
					despesa.setEmpenhoNumero(Integer.valueOf(fields[29]));
					despesa.setSubempenho(Integer.valueOf(fields[30]));
					despesa.setIndicadorSubempenho(fields[31]);
					despesa.setCredorCodigo(Integer.valueOf(fields[32]));
					despesa.setCredorNome(fields[33]);
					despesa.setModalidadeLicitacaoCodigo(Integer.valueOf(fields[34]));
					despesa.setModalidadeLicitacaoNome(fields[35]);
					despesa.setValorEmpenhado(Double.valueOf(fields[36].replace(",", ".")));
					despesa.setValorLiquidado(Double.valueOf(fields[37].replace(",", ".")));
					despesa.setValorPago(Double.valueOf(fields[38].replace(",", ".")));
					
					JPA.em().persist(despesa);
					
					if ((count++ % 10000) == 0){
						System.out.println("Registro: " + count);
					}
				}
				
				Logger.info("Success processing " + arquivo);
				
				br.close();
				
			} catch (Exception e){
				Logger.error("Erro processando arquivo " + arquivo + ": " + e.getLocalizedMessage());
				erros++;
			}
		//}
		return erros;
	}
	
}
