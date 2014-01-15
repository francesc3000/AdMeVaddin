package zone.adme.gwt.client;

import zone.adme.gwt.server.ControladorCore;
import zone.adme.gwt.shared.UsuarioGWT;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class AdMeGWT implements EntryPoint {
  
  //Declaración de las clases globales
  private SignIn signIn = new SignIn();
  private ControladorCore controladorCore = new ControladorCore();
  
  //Declaración de variables globales
  private UsuarioGWT usuarioGWT = null;
  ShowPubli sp = new ShowPubli();

  @Override
  public void onModuleLoad() {
	  
	  final Button b = new Button("Pulsa para crear usuario Francesc");
	    b.addClickHandler(new ClickHandler() {
	      @Override
	      public void onClick(ClickEvent e) {
	    	  controladorServicio.putUsuario(new UsuarioGWT("francesc3000@gmail.com"));
	      }
	    });
	  
		
	RootPanel.get().add(sp);


	  final Button c = new Button("Registrate con tu cuenta Google+");
		c.addClickHandler(new ClickHandler() {
		  @Override
		  public void onClick(ClickEvent e) {
			  signIn.login();
		    b.setVisible(false);
		  }
		});
    RootPanel.get().add(b);
  }
}
