package controllers;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import models.Total;
import models.educacao.Escola;
import models.saude.ContatoVacina;
import models.saude.UnidadeSaude;
import models.saude.Vacina;
import static models.Total.*;
import static models.saude.UnidadeSaude.*;
import static play.data.Form.form;

import play.Logger;
import play.data.DynamicForm;
import play.db.jpa.JPA;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import services.EducacaoService;
import services.FinancasService;
import services.SaudeService;
import util.DateUtil;

public class SaudeController extends Controller{

	@Transactional
	public static Result orcamento(){
		FinancasService financasService = new FinancasService();
		
		List<Total> despesaAnual = financasService.getGastos(DESPESA_POR_FUNCAO, "SAÚDE");
		List<Total> despesaTotal = financasService.getGastos(DESPESA_TOTAL);
		
		double porcentagem;
		for (int i = 0; i < despesaTotal.size(); i++){
			double anual = despesaAnual.get(i).getValor();
			double total = despesaTotal.get(i).getValor();
			porcentagem = (anual / total)*100;
			
			despesaAnual.get(i).setExtraInfo(new DecimalFormat("##.00").format(porcentagem) + "%");
		}
		
		return ok(views.html.orcamentosaude.render(despesaAnual, despesaTotal));
	}
	
	
	@Transactional
	public static Result unidadesSaude(long tipo){
		SaudeService service = new SaudeService();
		
		List<UnidadeSaude> todas = service.getUnidadesSaude(tipo);
		
		String tipoNome;
		if (tipo == TODAS){
			tipoNome = "Todas unidades de saúde";
		} else {
			tipoNome = todas.get(1).getTipoNome();
		}
			
		return ok(views.html.unidadessaude.render(tipoNome, tipo, todas));
	}
	
	/**
	 * Show details of escola id
	 * @param id
	 * @return
	 */
	
	@Transactional
	public static Result showUnidadeSaude(long id){
		UnidadeSaude unidade = new SaudeService().getUnidadeSaude(id);
		return ok(views.html.unidadesaudedetalhe.render(unidade));
	}
	
	@Transactional
	public static Result vacinacao(){
		List<Vacina> vacinas = new SaudeService().getVacinas();
		
		return ok(views.html.vacinas.render(vacinas));
	}
	
	@Transactional
	public static Result cadastraEmailVacina(){
		DynamicForm dynamicForm = form().bindFromRequest();
		String nome = dynamicForm.get("nome");
		String email = dynamicForm.get("email");
		String nasc = dynamicForm.get("nascimento");
		
		try {
			ContatoVacina cv = new ContatoVacina();
			cv.setNome(nome);
			cv.setEmail(email);
			cv.setNascimento(DateUtil.parseToDate(nasc));
		
    		JPA.em().persist(cv);
    		flash("message", "E-mail cadastrado com sucesso! " +
    				"Tentaremos informá-lo quando estiver perto da data das suas próximas vacinações.");
    	} catch (Exception ex){
    		flash("message", "Erro cadastrando e-mail. Por favor verifique os dados e tente novamente. (" + ex.getMessage() + ")");
    	}
    	
    	return redirect(routes.SaudeController.vacinacao());
	}
	
	/**
	 * Chama o configurador, que gera e trata o banco
	 * @return
	 */
	@Transactional
	public static Result configurar(){
		Date ini = new Date();
		
		SaudeService service = new SaudeService();
		service.processar();
		
		Date fim = new Date();
		Logger.info((fim.getTime() - ini.getTime())/1000 + " segundos");
		return ok("Foi");
	}
	

	
}
