package zone.adme.gwt.client;

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
  
  //Declaración de variables globales
  private UsuarioGWT usuarioGWT = null;

  @Override
  public void onModuleLoad() {   

    final Button b = new Button("Registrate con tu cuenta Google+");
    b.addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent e) {
    	  usuarioGWT = signIn.login();
        b.setVisible(false);
      }
    });
    RootPanel.get().add(b);
  }

  private void println(String msg) {
    RootPanel.get().add(new Label(msg));
  }
}
