package zone.adme.gwt.client;

import zone.adme.gwt.shared.UsuarioGWT;

import com.google.api.gwt.client.GoogleApiRequestTransport;
import com.google.api.gwt.client.OAuth2Login;
import com.google.api.gwt.services.plus.shared.Plus;
import com.google.api.gwt.services.plus.shared.Plus.ActivitiesContext.ListRequest.Collection;
import com.google.api.gwt.services.plus.shared.Plus.PeopleContext.GetRequest;
import com.google.api.gwt.services.plus.shared.Plus.PlusAuthScope;
import com.google.api.gwt.services.plus.shared.model.Activity;
import com.google.api.gwt.services.plus.shared.model.ActivityFeed;
import com.google.api.gwt.services.plus.shared.model.Person;
import com.google.api.gwt.shared.AuthScope;
import com.google.gwt.core.client.Callback;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.web.bindery.requestfactory.shared.Receiver;
/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class AdMeGWT implements EntryPoint {

  //Initialize the service proxy.
  private UsuarioServiceAsync usuarioService = GWT.create(UsuarioService.class);
  
  //Declaramos las clases a utilizar
  private SignIn signIn = new SignIn();

  @Override
  public void onModuleLoad() {
/*	  
      // Set up the callback object.
      AsyncCallback<UsuarioGWT> callback = new AsyncCallback<UsuarioGWT>() {
        public void onFailure(Throwable caught) {
        	println("Falla comunicación");
        }

        public void onSuccess(UsuarioGWT usuarioGWT) {
          println(usuarioGWT.getNombre());
        }
      };

      // Make the call to the stock price service.
      usuarioService.getUsuarioServer("francesc3000@gmail.com", callback);
  }
*/    

    final Button b = new Button("Registrate con tu cuenta Google+");
    b.addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent e) {
    	  signIn.login();
        b.setVisible(false);
      }
    });
    RootPanel.get().add(b);
  }

  private void println(String msg) {
    RootPanel.get().add(new Label(msg));
  }
}
