package zone.adme.gwt.client.views;

import com.google.api.gwt.client.GoogleApiRequestTransport;
import com.google.api.gwt.client.OAuth2Login;
import com.google.api.gwt.services.plus.shared.Plus;
import com.google.api.gwt.services.plus.shared.Plus.PlusAuthScope;
import com.google.api.gwt.services.plus.shared.Plus.ActivitiesContext.ListRequest.Collection;
import com.google.api.gwt.services.plus.shared.model.Activity;
import com.google.api.gwt.services.plus.shared.model.ActivityFeed;
import com.google.api.gwt.services.plus.shared.model.Person;
import com.google.gwt.core.client.Callback;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.web.bindery.requestfactory.shared.Receiver;

import zone.adme.gwt.client.services.UserService;
import zone.adme.gwt.client.services.UserServiceAsync;
import zone.adme.gwt.shared.UsuarioGWT;

public class SignIn {
	
	private SignUI padre = null;
	
	private UsuarioGWT usuarioGWT = null;
	
	private static final Plus plus = GWT.create(Plus.class);
	private static final String CLIENT_ID = "480216243468-lk40r99ktga7djtdcukdbjf8tee2tq0f.apps.googleusercontent.com";
	private static final String API_KEY = "AIzaSyDXT4x-PbIHeqORMaJxWT2eZht7oySMZXw";
	private static final String APPLICATION_NAME = "AdMeGWT/1.0";
	private final UserServiceAsync signService = GWT.create(UserService.class);
	
	
	public SignIn(SignUI padre){
		plus.initialize(new SimpleEventBus(), new GoogleApiRequestTransport(APPLICATION_NAME, API_KEY));
		this.padre = padre;
	}
	
	public boolean login(String usuario, String contrasena){
		signService.signIn(usuario, contrasena, new AsyncCallback<UsuarioGWT>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Fallo conexion");
				
			}

			@Override
			public void onSuccess(UsuarioGWT usuarioGWT) {
				setUsuarioGWT(usuarioGWT);
			}
			
			
		});
	    
	    return true;
    }
	
	private boolean setUsuarioGWT(UsuarioGWT usuarioGWT){
		this.padre.setUsuarioGWT(usuarioGWT);
		
		return true;
	}
		
	private void getMe() {	 
	    plus.people().get("me").to(new Receiver<Person>() {
		  @Override
		  public void onSuccess(Person person) {
		    println("Hola " + person.getDisplayName());
		    println("Tu foto " + person.getImage());
		
		    getMyActivities();
		    login(person.getEmails().get(0).getValue().toString(),"");
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
	    }).fire();
	
	}
	private void println(String msg) {
		RootPanel.get().add(new Label(msg));
	}

	public UsuarioGWT getUsuarioSession() {
		return usuarioGWT;
	}

	public void googleIn() {
	    OAuth2Login.get().authorize(CLIENT_ID, PlusAuthScope.USERINFO_EMAIL, new Callback<Void, Exception>() {
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
}
