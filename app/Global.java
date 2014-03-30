import play.*;
import play.mvc.*;
import play.mvc.Http.RequestHeader;

import static play.mvc.Results.*;

public class Global extends GlobalSettings {

  @Override
  public Result onHandlerNotFound(RequestHeader request) {
    return Results.notFound(
      views.html.erro404.render(request.uri())
    );
  }  
    
}