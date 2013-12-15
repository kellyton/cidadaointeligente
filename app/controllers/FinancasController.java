package controllers;

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
	
	/**
	 * Mostra as estatísticas de finança por ano (arg)
	 */
	@Transactional
	public static Result show(String arg){
		
		int ano = 2013;
		
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
	
}
