package controllers;

import static play.data.Form.form;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import models.Contato;
import models.saude.ContatoVacina;
import play.*;
import play.data.DynamicForm;
import play.db.jpa.JPA;
import play.db.jpa.Transactional;
import play.libs.Akka;
import play.mvc.*;

import scala.concurrent.duration.Duration;
import util.DateUtil;
import views.html.*;

public class Application extends Controller {
  
	//Start email checker
	//static 	
	{
		Akka.system().scheduler().schedule(
				Duration.create(0, TimeUnit.MILLISECONDS),
				Duration.create(10, TimeUnit.SECONDS),
				new Runnable() {
					public void run() {
						Logger.info("Rodando o check de vacinas " + new Date());
						SaudeController.checkVacinas();
					}
				},
				Akka.system().dispatcher()
			);

	}
	
    public static Result index() {
        return ok(index.render());
    }
    
    public static Result sobre(){
		return ok(views.html.sobre.render());
	} 
    
	@Transactional
	public static Result cadastraEmail(){
		DynamicForm dynamicForm = form().bindFromRequest();
		String nome = dynamicForm.get("nome");
		String email = dynamicForm.get("email");
		
		try {
			Contato c = new Contato();
			c.setNome(nome);
			c.setEmail(email);
		
    		JPA.em().persist(c);
    		flash("message", "E-mail cadastrado com sucesso!");
    	} catch (Exception ex){
    		flash("message", "Erro cadastrando e-mail. Por favor verifique os dados e tente novamente. (" + ex.getMessage() + ")");
    	}
    	
    	return redirect(routes.Application.index());
	}
  
}
