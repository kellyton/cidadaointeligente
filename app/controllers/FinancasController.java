package controllers;

import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import services.FinancasService;

public class FinancasController extends Controller{

	@Transactional
	public Result configurarFinancas(){
		FinancasService service = new FinancasService();
		service.processarDespesas();
		return ok("Foi");
	}
	
}
