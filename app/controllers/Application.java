package controllers;

import static play.data.Form.form;
import models.Contato;
import models.saude.ContatoVacina;
import play.*;
import play.data.DynamicForm;
import play.db.jpa.JPA;
import play.db.jpa.Transactional;
import play.mvc.*;

import util.DateUtil;
import views.html.*;

public class Application extends Controller {
  
    public static Result index() {
        return ok(index.render());
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
