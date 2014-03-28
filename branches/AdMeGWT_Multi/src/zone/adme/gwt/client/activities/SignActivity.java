package zone.adme.gwt.client.activities;

import javax.inject.Inject;

import zone.adme.gwt.client.events.UserNotRegisteredEvent;
import zone.adme.gwt.client.events.UserRegisteredEvent;
import zone.adme.gwt.client.mapper.PlaceControllerHolder;
import zone.adme.gwt.client.places.PControlPlace;
import zone.adme.gwt.client.places.RegisterPlace;
import zone.adme.gwt.client.services.UserService;
import zone.adme.gwt.client.services.UserServiceAsync;
import zone.adme.gwt.client.views.interfaces.SignView;
import zone.adme.gwt.shared.UsuarioGWT;

import com.google.api.gwt.client.GoogleApiRequestTransport;
import com.google.api.gwt.client.OAuth2Login;
import com.google.api.gwt.services.plus.shared.Plus;
import com.google.api.gwt.services.plus.shared.Plus.PlusAuthScope;
import com.google.api.gwt.services.plus.shared.Plus.ActivitiesContext.ListRequest.Collection;
import com.google.api.gwt.services.plus.shared.model.ActivityFeed;
import com.google.api.gwt.services.plus.shared.model.Person;
import com.google.gwt.activity.shared.Activity;
import com.google.gwt.core.client.Callback;
import com.google.gwt.core.client.GWT;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.web.bindery.event.shared.HandlerRegistration;
import com.google.web.bindery.event.shared.binder.EventBinder;
import com.google.web.bindery.event.shared.binder.EventHandler;
import com.google.web.bindery.requestfactory.shared.Receiver;

public class SignActivity extends BaseActivity<SignView> implements Activity, SignView.Presenter{
	interface MyEventBinder extends EventBinder<SignActivity> {}
	private static final MyEventBinder eventBinder = GWT.create(MyEventBinder.class);
	
	@Inject
    PlaceControllerHolder placeControllerHolder;
	
	//Variables de evento
	private HandlerRegistration eventRegistration = null;
	
	//Variables de ejecución
	private UsuarioGWT usuarioGWT = null;
	private static final Plus plus = GWT.create(Plus.class);
	private static final String CLIENT_ID = "480216243468-lk40r99ktga7djtdcukdbjf8tee2tq0f.apps.googleusercontent.com";
	private static final String API_KEY = "AIzaSyDXT4x-PbIHeqORMaJxWT2eZht7oySMZXw";
	private static final String APPLICATION_NAME = "AdMeGWT/1.0";
	private final UserServiceAsync userService = GWT.create(UserService.class);
	
	public void login(String usuario, String contrasena){
		
		plus.initialize(this.placeControllerHolder.getEventBus(), new GoogleApiRequestTransport(APPLICATION_NAME, API_KEY));
		
		userService.signIn(usuario, contrasena, new AsyncCallback<UsuarioGWT>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Fallo conexion");
				
			}

			@Override
			public void onSuccess(UsuarioGWT usuarioGWT) {
				setUsuarioGWT(usuarioGWT);
			}
			
			
		});
    }
	
	private boolean setUsuarioGWT(UsuarioGWT usuarioGWT){
		if(usuarioGWT!=null){
			if(usuarioGWT.getMensaje()!=null){
				Window.alert(usuarioGWT.getMensaje());
			}else{
				this.view.userRegistered("Hola " + usuarioGWT.getNombre());
				this.placeControllerHolder.getEventBus().fireEvent(new UserRegisteredEvent(usuarioGWT));
			}
		}else{
			this.view.disable();
			this.placeControllerHolder.getEventBus().fireEvent(new UserNotRegisteredEvent());
			this.placeControllerHolder.getPlaceController().goTo(new RegisterPlace("Registro"));
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
	          for (com.google.api.gwt.services.plus.shared.model.Activity a : feed.getItems()) {
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
	
	public void pControlClicked(){
		this.placeControllerHolder.getPlaceController().goTo(new PControlPlace("PControl"));
	}
	
	@EventHandler
	 void onUserRegistered(UserRegisteredEvent event) {
	    this.view.userRegistered(event.getUsuarioGWT().getNombre());
	 }
	
	@Override
	public void startHandler(){
		if(this.eventRegistration==null){
			this.eventRegistration = eventBinder.bindEventHandlers(this, this.placeControllerHolder.getEventBus());
		}
	}

	@Override
	public void stopHandler() {
		if(this.eventRegistration!=null){
			eventRegistration.removeHandler();
		}
	}

	@Override
	public void goTo(Place place) {
		this.placeControllerHolder.getPlaceController().goTo(place);
	}

	@Override
	public void onStop() {
		this.stopHandler();
	}

	@Override
	public void onStart(AcceptsOneWidget panel) {
		panel.setWidget(getView());
        getView().setPresenter(this);
        this.startHandler();
	}
}
