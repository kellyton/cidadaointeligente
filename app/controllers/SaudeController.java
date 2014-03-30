package controllers;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;

import models.Total;
import models.saude.ContatoVacina;
import models.saude.DosePessoa;
import models.saude.PessoaDoses;
import models.saude.UnidadeSaude;
import models.saude.Vacina;
import models.saude.VacinaBak;
import static models.Total.*;
import static models.saude.UnidadeSaude.*;
import static play.data.Form.form;

import play.Logger;
import play.data.DynamicForm;
import play.db.jpa.JPA;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import services.FinancasService;
import services.MailService;
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
		List<VacinaBak> vacinas = new SaudeService().getVacinasBak();
		
		return ok(views.html.vacinas.render(vacinas));
	}
	
	@Transactional
	public static Result cadastraEmailVacina(){
		DynamicForm dynamicForm = form().bindFromRequest();
		String nome = dynamicForm.get("nome");
		String email = dynamicForm.get("email");
		String nasc = dynamicForm.get("nascimento");
		
		if (
				(nome == null || nome.trim().isEmpty()) ||
				(email == null || email.trim().isEmpty()) ||
				(nasc == null || nasc.trim().isEmpty())
				){
			flash("messageVacina", "Por favor informe todos os dados para o cadastro.");
			
			return redirect(routes.SaudeController.vacinacao());
		}
		
		try {
			ContatoVacina cv = new ContatoVacina();
			cv.setNome(nome);
			cv.setEmail(email);
			cv.setNascimento(DateUtil.parseToDate(nasc));
		
    		JPA.em().persist(cv);
    		
    		generateDoses(cv);
    		
    		sendMailBoasVindas(cv);
    		
    		return abreCartao(email);
    		
    		//flash("messageVacina", "E-mail cadastrado com sucesso! " +
    		//		"Tentaremos informá-lo quando estiver perto da data das suas próximas vacinações.");
    	} catch (Exception ex){
    		flash("messageVacina", "Erro cadastrando e-mail. Por favor verifique os dados e tente novamente. (" + ex.getMessage() + ")");
    		ex.printStackTrace();
    	}
    	
    	return redirect(routes.SaudeController.vacinacao());
	}
	
	private static void sendMailBoasVindas(ContatoVacina user) {
		SaudeService saudeService = new SaudeService();
		MailService mailService = new MailService();
		
		List<DosePessoa> dosesProximas = saudeService.getProximasDoses(user, 2);
		List<DosePessoa> dosesUltimas = saudeService.getUltimasDoses(user, 2);
		mailService.sendEmailBoasVindas(user, dosesProximas, dosesUltimas);
	}
	
	@Transactional
	public static Result cartaoVacinacao(){
		DynamicForm dynamicForm = form().bindFromRequest();
		String email = dynamicForm.get("email");
		
		return abreCartao(email);
	}
	
	@Transactional
	public static Result cartaoVacinacaoShow(long id){
		ContatoVacina cv = new SaudeService().getUsuario(id);
		
		if (cv != null) {
			return abreCartao(cv.getEmail());
		} else {
			return redirect(routes.SaudeController.vacinacao());
		}
	}
	
	public static Result abreCartao(String email){
		SaudeService service = new SaudeService();
		
		try {
			List<ContatoVacina> users = service.getUsuarios(email);
			
			if (users.isEmpty()) {
				throw new Exception("E-mail não cadastrado");
			}
			
			List<PessoaDoses> pessoaDoses = new ArrayList<PessoaDoses>();
			PessoaDoses pd;
			
			for (ContatoVacina user: users){
				List<DosePessoa> doses = service.getDoses(user);
				pd = new PessoaDoses();
				pd.setPessoa(user);
				pd.setDoses(doses);
				pessoaDoses.add(pd);
			}
			
			return ok(views.html.cartaovacinacao.render(pessoaDoses));

    	} catch (Exception ex){
    		flash("messageLogin", "Erro retornando dados. Tem certeza que você cadastrou este e-mail? (" + ex.getMessage() + ")");
    		Logger.error(ex.getMessage(), ex);
    		return redirect(routes.SaudeController.vacinacao());
    	}
	}
	
	/*public static Result abreCartaoBak(String email){
		SaudeService service = new SaudeService();
		
		try {
			List<ContatoVacina> users = service.getUsuarios(email);
			ContatoVacina user = users.get(0);
			
			//TODO: Por enquanto é só um
			List<DosePessoa> doses = service.getDoses(user);
			
			return ok(views.html.cartaovacinacao.render(user, doses));

    	} catch (Exception ex){
    		flash("messageLogin", "Erro retornando dados. Tem certeza que você cadastrou este e-mail? (" + ex.getMessage() + ")");
    		Logger.error(ex.getMessage(), ex);
    		return redirect(routes.SaudeController.vacinacao());
    	}
	}*/
	
	private static void generateDoses(ContatoVacina cv) {
		SaudeService service = new SaudeService();
		List<Vacina> vacinas = service.getVacinas();
		
		Date d = cv.getNascimento();
		DateTime dataBase = new DateTime(d);
		
		DosePessoa dp;
		for (Vacina vacina: vacinas){
			dp = new DosePessoa();
			dp.setPessoa(cv);
			dp.setVacina(vacina);
			dp.setDataPrevista(dataBase.plusDays(vacina.getDiaDeAplicacao()));
			
			JPA.em().persist(dp);
		}
		
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
	
	@Transactional
	public static void checkVacinas() {
		JPA.withTransaction(new play.libs.F.Callback0() {
			@Override
			public void invoke() throws Throwable {
				//before and after 72 hours
				List<DosePessoa> doses = new SaudeService().getProximasDoses(72);
				MailService mailService = new MailService();
				mailService.sendEmailAviso(doses);
			}
		});
	}
	
}
