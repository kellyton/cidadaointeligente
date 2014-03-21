package controllers;

import static models.Total.*;
import static models.cultura.Cultura.*;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

import models.Total;
import models.cultura.Cultura;
import models.educacao.Escola;
import models.saude.UnidadeSaude;

import play.Logger;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import services.CulturaService;
import services.EducacaoService;
import services.FinancasService;
import services.SaudeService;

public class CulturaController extends Controller{
	
	@Transactional
	public static Result orcamento(){
		FinancasService financasService = new FinancasService();
		
		List<Total> despesaAnual = financasService.getGastos(DESPESA_POR_FUNCAO, "CULTURA");
		List<Total> despesaTotal = financasService.getGastos(DESPESA_TOTAL);
		
		double porcentagem;
		for (int i = 0; i < despesaTotal.size(); i++){
			double anual = despesaAnual.get(i).getValor();
			double total = despesaTotal.get(i).getValor();
			porcentagem = (anual / total)*100;
			
			despesaAnual.get(i).setExtraInfo(new DecimalFormat("##.00").format(porcentagem) + "%");
		}
		
		return ok(views.html.orcamentocultura.render(despesaAnual, despesaTotal));
	}
	
	@Transactional
	public static Result pontos(long tipo){
		CulturaService service = new CulturaService();
		
		List<Cultura> todas = service.getEquipamentos(tipo);
		
		String tipoNome;
		if (tipo == TODAS){
			tipoNome = "Todos pontos culturais/turísticos";
		} else {
			tipoNome = todas.get(1).getTipoNome();
		}
			
		return ok(views.html.cultura.render(tipoNome, tipo, todas));
		
	}

	/**
	 * Show details of escola id
	 * @param id
	 * @return
	 */
	
	@Transactional
	public static Result showPonto(long id){
		Cultura cultura = new CulturaService().getEquipamento(id);
		return ok(views.html.culturadetalhe.render(cultura));
	}
	
	/**
	 * Chama o configurador, que gera e trata o banco
	 * @return
	 */
	@Transactional
	public static Result configurar(){
		Date ini = new Date();
		
		CulturaService service = new CulturaService();
		service.processar();
		
		Date fim = new Date();
		Logger.info((fim.getTime() - ini.getTime())/1000 + " segundos");
		return ok("Foi");
	}
	
}
