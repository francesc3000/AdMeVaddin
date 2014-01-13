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

  private static final Plus plus = GWT.create(Plus.class);
  private static final String CLIENT_ID = "480216243468-lk40r99ktga7djtdcukdbjf8tee2tq0f.apps.googleusercontent.com";
  private static final String API_KEY = "AIzaSyDXT4x-PbIHeqORMaJxWT2eZht7oySMZXw";
  private static final String APPLICATION_NAME = "AdMe/1.0";

  //Initialize the service proxy.
  private UsuarioServiceAsync usuarioService = GWT.create(UsuarioService.class);

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
	  
    plus.initialize(new SimpleEventBus(), new GoogleApiRequestTransport(APPLICATION_NAME, API_KEY));

    final Button b = new Button("Registrate con tu cuenta Google+");
    b.addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent e) {
        login();
        b.setVisible(false);
      }
    });
    RootPanel.get().add(b);
  }

  private void login() {
    OAuth2Login.get().authorize(CLIENT_ID, PlusAuthScope.PLUS_ME, new Callback<Void, Exception>() {
      @Override
      public void onSuccess(Void v) {
        getMe();
      }

      @Override
      public void onFailure(Exception e) {
        println(e.getMessage());
      }
    });
  }

  private void getMe() {	  
    plus.people().get("me").to(new Receiver<Person>() {
      @Override
      public void onSuccess(Person person) {
        println("Hola " + person.getDisplayName());
        println("Tu foto " + person.getImage());

        //getMyActivities();
      }
    }).fire();
  }

  private void getMyActivities() {
    plus.activities().list("me", Collection.PUBLIC).to(new Receiver<ActivityFeed>() {
      @Override
      public void onSuccess(ActivityFeed feed) {
        println("===== PUBLIC ACTIVITIES =====");
        if (feed.getItems() == null || feed.getItems().isEmpty()) {
          println("You have no public activities");
        } else {
          for (Activity a : feed.getItems()) {
            println(a.getTitle());
          }
        }
      }
      public void onFailure(Exception e){
    	  
      }
    }).fire();
  }

  private void println(String msg) {
    RootPanel.get().add(new Label(msg));
  }
}
