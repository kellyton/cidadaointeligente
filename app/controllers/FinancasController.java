package controllers;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

import models.Total;
import static models.Total.*;

import play.Logger;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import services.FinancasService;

public class FinancasController extends Controller{

	
	@Transactional
	public static Result showCompareOrcamentoAnual(){
		FinancasService service = new FinancasService();
		
		List<Total> total = service.getGastos(DESPESA_TOTAL);
		
		List<Total> urbanismo = service.getGastos(DESPESA_POR_FUNCAO, "URBANISMO");
		List<Total> saude = service.getGastos(DESPESA_POR_FUNCAO, "SAÚDE");
		List<Total> administracao = service.getGastos(DESPESA_POR_FUNCAO, "ADMINISTRAÇÃO");
		List<Total> educacao = service.getGastos(DESPESA_POR_FUNCAO, "EDUCAÇÃO");
		List<Total> previdencia = service.getGastos(DESPESA_POR_FUNCAO, "PREVIDÊNCIA SOCIAL");
		List<Total> cultura = service.getGastos(DESPESA_POR_FUNCAO, "CULTURA");
		List<Total> encargos = service.getGastos(DESPESA_POR_FUNCAO, "ENCARGOS ESPECIAIS");
		List<Total> saneamento = service.getGastos(DESPESA_POR_FUNCAO, "SANEAMENTO");
		List<Total> assistencia = service.getGastos(DESPESA_POR_FUNCAO, "ASSISTÊNCIA SOCIAL");
		List<Total> ambiental = service.getGastos(DESPESA_POR_FUNCAO, "GESTÃO AMBIENTAL");
		List<Total> comunicacoes = service.getGastos(DESPESA_POR_FUNCAO, "COMUNICAÇÕES");
		List<Total> comercio = service.getGastos(DESPESA_POR_FUNCAO, "COMÉRCIO E SERVIÇOS");
		List<Total> habitacao = service.getGastos(DESPESA_POR_FUNCAO, "HABITAÇÃO");
		List<Total> desporto = service.getGastos(DESPESA_POR_FUNCAO, "DESPORTO E LAZER");
		List<Total> cidadania = service.getGastos(DESPESA_POR_FUNCAO, "DIREITOS DA CIDADANIA");
		List<Total> ciencia = service.getGastos(DESPESA_POR_FUNCAO, "CIÊNCIA E TECNOLOGIA");
		List<Total> trabalho = service.getGastos(DESPESA_POR_FUNCAO, "TRABALHO");
		
		//TODO dá pra colocar isso em um for
		double porcentagem;
		for (int i = 0; i < urbanismo.size(); i++){
			double anu = total.get(i).getValor();
			double tot = urbanismo.get(i).getValor();
			porcentagem = (tot / anu)*100;
			
			urbanismo.get(i).setExtraInfo(new DecimalFormat("##.00").format(porcentagem) + "%");
		}
		for (int i = 0; i < saude.size(); i++){
			double anu = total.get(i).getValor();
			double tot = saude.get(i).getValor();
			porcentagem = (tot / anu)*100;
			
			saude.get(i).setExtraInfo(new DecimalFormat("##.00").format(porcentagem) + "%");
		}
		for (int i = 0; i < administracao.size(); i++){
			double anu = total.get(i).getValor();
			double tot = administracao.get(i).getValor();
			porcentagem = (tot / anu)*100;
			
			administracao.get(i).setExtraInfo(new DecimalFormat("##.00").format(porcentagem) + "%");
		}
		for (int i = 0; i < educacao.size(); i++){
			double anu = total.get(i).getValor();
			double tot = educacao.get(i).getValor();
			porcentagem = (tot / anu)*100;
			
			educacao.get(i).setExtraInfo(new DecimalFormat("##.00").format(porcentagem) + "%");
		}
		for (int i = 0; i < previdencia.size(); i++){
			double anu = total.get(i).getValor();
			double tot = previdencia.get(i).getValor();
			porcentagem = (tot / anu)*100;
			
			previdencia.get(i).setExtraInfo(new DecimalFormat("##.00").format(porcentagem) + "%");
		}
		for (int i = 0; i < cultura.size(); i++){
			double anu = total.get(i).getValor();
			double tot = cultura.get(i).getValor();
			porcentagem = (tot / anu)*100;
			
			cultura.get(i).setExtraInfo(new DecimalFormat("##.00").format(porcentagem) + "%");
		}
		for (int i = 0; i < encargos.size(); i++){
			double anu = total.get(i).getValor();
			double tot = encargos.get(i).getValor();
			porcentagem = (tot / anu)*100;
			
			encargos.get(i).setExtraInfo(new DecimalFormat("##.00").format(porcentagem) + "%");
		}
		for (int i = 0; i < saneamento.size(); i++){
			double anu = total.get(i).getValor();
			double tot = saneamento.get(i).getValor();
			porcentagem = (tot / anu)*100;
			
			saneamento.get(i).setExtraInfo(new DecimalFormat("##.00").format(porcentagem) + "%");
		}
		for (int i = 0; i < assistencia.size(); i++){
			double anu = total.get(i).getValor();
			double tot = assistencia.get(i).getValor();
			porcentagem = (tot / anu)*100;
			
			assistencia.get(i).setExtraInfo(new DecimalFormat("##.00").format(porcentagem) + "%");
		}
		for (int i = 0; i < ambiental.size(); i++){
			double anu = total.get(i).getValor();
			double tot = ambiental.get(i).getValor();
			porcentagem = (tot / anu)*100;
			
			ambiental.get(i).setExtraInfo(new DecimalFormat("##.00").format(porcentagem) + "%");
		}
		for (int i = 0; i < comunicacoes.size(); i++){
			double anu = total.get(i).getValor();
			double tot = comunicacoes.get(i).getValor();
			porcentagem = (tot / anu)*100;
			
			comunicacoes.get(i).setExtraInfo(new DecimalFormat("##.00").format(porcentagem) + "%");
		}
		for (int i = 0; i < comercio.size(); i++){
			double anu = total.get(i).getValor();
			double tot = comercio.get(i).getValor();
			porcentagem = (tot / anu)*100;
			
			comercio.get(i).setExtraInfo(new DecimalFormat("##.00").format(porcentagem) + "%");
		}
		for (int i = 0; i < habitacao.size(); i++){
			double anu = total.get(i).getValor();
			double tot = habitacao.get(i).getValor();
			porcentagem = (tot / anu)*100;
			
			habitacao.get(i).setExtraInfo(new DecimalFormat("##.00").format(porcentagem) + "%");
		}
		for (int i = 0; i < desporto.size(); i++){
			double anu = total.get(i).getValor();
			double tot = desporto.get(i).getValor();
			porcentagem = (tot / anu)*100;
			
			desporto.get(i).setExtraInfo(new DecimalFormat("##.00").format(porcentagem) + "%");
		}
		for (int i = 0; i < cidadania.size(); i++){
			double anu = total.get(i).getValor();
			double tot = cidadania.get(i).getValor();
			porcentagem = (tot / anu)*100;
			
			cidadania.get(i).setExtraInfo(new DecimalFormat("##.00").format(porcentagem) + "%");
		}
		for (int i = 0; i < ciencia.size(); i++){
			double anu = total.get(i).getValor();
			double tot = ciencia.get(i).getValor();
			porcentagem = (tot / anu)*100;
			
			ciencia.get(i).setExtraInfo(new DecimalFormat("##.00").format(porcentagem) + "%");
		}
		for (int i = 0; i < trabalho.size(); i++){
			double anu = total.get(i).getValor();
			double tot = trabalho.get(i).getValor();
			porcentagem = (tot / anu)*100;
			
			trabalho.get(i).setExtraInfo(new DecimalFormat("##.00").format(porcentagem) + "%");
		}
		
		return ok(views.html.orcamentogeral.render(total, urbanismo, saude, administracao, educacao,
				previdencia, cultura, encargos, saneamento, assistencia, ambiental, comunicacoes,
				comercio, habitacao, desporto, cidadania, ciencia, trabalho));
	}
	
	/**
	 * Mostra as estatísticas de finança por ano (arg)
	 */
	@Transactional
	public static Result show(String arg){
		
		int ano;
		
		try {
			ano = Integer.parseInt(arg);
		} catch (Exception e){
			return redirect(controllers.routes.Application.index());
		}
		
		FinancasService service = new FinancasService();
		
		List<Total> despesasPorFuncao = service.getGastos(DESPESA_POR_FUNCAO, ano);
		List<Total> despesasPorElemento = service.getGastos(DESPESA_POR_ELEMENTO, ano);
		List<Total> despesasPorOrgao = service.getGastos(DESPESA_POR_ORGAO, ano);
		List<Total> despesasPorCredor = service.getGastos(DESPESA_POR_CREDOR, ano, 500);
		
		return ok(views.html.financas.render(ano, despesasPorFuncao, despesasPorElemento, despesasPorOrgao, despesasPorCredor));
		
	}
	
	/**
	 * Chama o configurador, que gera e trata o banco
	 * @return
	 */
	@Transactional
	public static Result configurar(int ano){
		Date ini = new Date();
		
		FinancasService service = new FinancasService();
		service.processar(ano);
		
		Date fim = new Date();
		Logger.info((fim.getTime() - ini.getTime())/1000 + " segundos");
		return ok("Foi");
	}
	
	/**
	 * Chama o configurador, que gera e trata o banco
	 * @return
	 */
	@Transactional
	public static Result configurar(){
		Date ini = new Date();
		
		FinancasService service = new FinancasService();
		service.processar();
		
		Date fim = new Date();
		Logger.info((fim.getTime() - ini.getTime())/1000 + " segundos");
		return ok("Foi");
	}
	
}
