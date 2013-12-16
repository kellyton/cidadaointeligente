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
import static models.educacao.Escola.*;

public class EducacaoController extends Controller{

	@Transactional
	public static Result orcamento(){
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
		
		return ok(views.html.orcamentoeducacao.render(despesaAnual, despesaTotal));
	}
	
	@Transactional
	public static Result escolas(long tipo){
		EducacaoService educacaoService = new EducacaoService();
		
		String tipoNome;
		List<Escola> escolas;
		if (tipo == TODAS){
			escolas = educacaoService.getEscolas();
		} else {
			escolas = educacaoService.getEscolas(tipo);
		}
		
		if (tipo == ESCOLA){
			tipoNome = "Escolas";
		} else if (tipo == CRECHE){
			tipoNome = "Creches";
		} else {
			tipoNome = "Todas unidades educacionais";
		}
		
		return ok(views.html.escolas.render(tipoNome, tipo, escolas));
		
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
	
}
