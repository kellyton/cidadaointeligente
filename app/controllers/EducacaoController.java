package controllers;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

import models.Total;
import models.educacao.Escola;
import static models.Total.*;

import play.Logger;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import services.EducacaoService;
import services.FinancasService;

public class EducacaoController extends Controller{

	@Transactional
	public static Result index(){
		FinancasService financasService = new FinancasService();
		
		List<Total> despesaAnual = financasService.getGastos(DESPESA_POR_FUNCAO, "EDUCAÇÃO");
		List<Total> despesaTotal = financasService.getGastos(DESPESA_TOTAL);
		
		double porcentagem;
		for (int i = 0; i < despesaTotal.size(); i++){
			double anual = despesaAnual.get(i).getValor();
			double total = despesaTotal.get(i).getValor();
			porcentagem = (anual / total)*100;
			
			despesaAnual.get(i).setExtraInfo(new DecimalFormat("##.00").format(porcentagem) + "%");
		}
		
		return ok(views.html.educacao.render(despesaAnual, despesaTotal));
	}
	
	@Transactional
	public static Result escolas(){
		EducacaoService educacaoService = new EducacaoService();
		
		List<Escola> escolas = educacaoService.getEscolas();
		return ok(views.html.escolas.render(escolas));
		
	}

	/**
	 * Show details of escola id
	 * @param id
	 * @return
	 */
	
	@Transactional
	public static Result showEscola(long id){
		Escola escola = new EducacaoService().getEscola(id);
		return ok(views.html.escoladetalhe.render(escola));
	}
	
	/**
	 * Chama o configurador, que gera e trata o banco
	 * @return
	 */
	@Transactional
	public static Result configurar(){
		Date ini = new Date();
		
		EducacaoService service = new EducacaoService();
		service.processar();
		
		Date fim = new Date();
		Logger.info((fim.getTime() - ini.getTime())/1000 + " segundos");
		return ok("Foi");
	}
	
	/**
	 * Mostra a escola pelo ID
	 */
	@Transactional
	public static Result show(String arg){
		
		/*int ano = 2013;
		
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
		
		return ok(views.html.financas.render(ano, despesasPorFuncao, despesasPorElemento, despesasPorOrgao, despesasPorCredor));*/
		return TODO;
		
	}
	
}
