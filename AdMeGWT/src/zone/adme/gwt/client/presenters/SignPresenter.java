package zone.adme.gwt.client.presenters;

import zone.adme.gwt.client.events.PControlClickedEvent;
import zone.adme.gwt.client.events.UserNotRegisteredEvent;
import zone.adme.gwt.client.events.UserRegisteredEvent;
import zone.adme.gwt.client.services.UserService;
import zone.adme.gwt.client.services.UserServiceAsync;
import zone.adme.gwt.shared.UsuarioGWT;

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
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.event.shared.HandlerRegistration;
import com.google.web.bindery.event.shared.binder.EventBinder;
import com.google.web.bindery.requestfactory.shared.Receiver;

public class SignPresenter implements Presenter{
	interface MyEventBinder extends EventBinder<SignPresenter> {}
	private static final MyEventBinder eventBinder = GWT.create(MyEventBinder.class);
	
	//Variables de evento
	private EventBus eventBus=null;
	private HandlerRegistration eventRegistration = null;
	
	//Variables de vista
	private Display view = null;
	
	//Variables de ejecución
	private UsuarioGWT usuarioGWT = null;
	private static final Plus plus = GWT.create(Plus.class);
	private static final String CLIENT_ID = "480216243468-lk40r99ktga7djtdcukdbjf8tee2tq0f.apps.googleusercontent.com";
	private static final String API_KEY = "AIzaSyDXT4x-PbIHeqORMaJxWT2eZht7oySMZXw";
	private static final String APPLICATION_NAME = "AdMeGWT/1.0";
	private final UserServiceAsync signService = GWT.create(UserService.class);	
	
	public interface Display{
		public void clear();
		public void disable();
		void userRegistered(String userText);
		public Widget asWidget();
		public boolean setPresenter(SignPresenter presenter);
	}
	
	public SignPresenter(Display view, EventBus eventBus){
		this.eventBus = eventBus;
		this.view = view;
		
		bind();
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
		if(usuarioGWT!=null){
			if(usuarioGWT.getMensaje()!=null){
				Window.alert(usuarioGWT.getMensaje());
			}else{
				this.view.userRegistered("Hola " + usuarioGWT.getNombre());
				this.eventBus.fireEvent(new UserRegisteredEvent(usuarioGWT));
			}
		}else{
			this.eventBus.fireEvent(new UserNotRegisteredEvent());
		}
		
		this.usuarioGWT = usuarioGWT;		
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
	
	public void PControlClicked(){
		this.eventBus.fireEvent(new PControlClickedEvent(this.usuarioGWT));
	}
	
	@Override
	public boolean bind() {
		this.view.setPresenter(this);
		plus.initialize(this.eventBus, new GoogleApiRequestTransport(APPLICATION_NAME, API_KEY));
		return true;
	}

	@Override
	public boolean start() {
		if(this.eventRegistration==null){
			this.eventRegistration = eventBinder.bindEventHandlers(this, this.eventBus);
		}
		return true;
	}

	@Override
	public boolean stop() {
		if(this.eventRegistration!=null){
			eventRegistration.removeHandler();
		}
		return true;
	}
}
