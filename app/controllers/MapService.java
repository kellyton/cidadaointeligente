/* Copyright 2013 de Kellyton Brito. Este arquivo é parte 
* do programa MeuCongressoNacional.com . O MeuCongressoNacional.com 
* é um software livre; você pode redistribuí-lo e/ou modificá-lo 
* dentro dos termos da GNU Affero General Public License como 
* publicada pela Fundação do Software Livre (FSF) na versão 3 
* da Licença. Este programa é distribuído na esperança que possa 
* ser útil, mas SEM NENHUMA GARANTIA; sem uma garantia implícita 
* de ADEQUAÇÃO a qualquer MERCADO ou APLICAÇÃO EM PARTICULAR. Veja 
* a licença para maiores detalhes, disponível em 
* meucongressonacional.com/license. Você deve ter recebido uma cópia 
* da GNU Affero General Public License, sob o título "LICENCA.txt", 
* junto com este programa, se não, acesse http://www.gnu.org/licenses/
**/

package controllers;

import java.util.List;

import models.educacao.Escola;
import play.db.jpa.Transactional;
import play.mvc.*;
import services.EducacaoService;

public class MapService extends Controller {
	
	@Transactional
	public static Result showMapEscolas(){
		List<Escola> escolas = new EducacaoService().getEscolas();
		return ok(views.html.mapaescolas.render(escolas));
	}
	
	@Transactional
	public static Result showMapEscola(long id){
		Escola escola = new EducacaoService().getEscola(id);
		return ok(views.html.mapaescolaindividual.render(escola));
	}
}