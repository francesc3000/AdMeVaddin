package zone.adme.gwt.client.activities;

import javax.inject.Inject;

import zone.adme.gwt.client.events.UserNotRegisteredEvent;
import zone.adme.gwt.client.events.UserRegisteredEvent;
import zone.adme.gwt.client.mapper.PlaceControllerHolder;
import zone.adme.gwt.client.services.UserService;
import zone.adme.gwt.client.views.interfaces.RegisterView;
import zone.adme.gwt.shared.UsuarioGWT;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.core.client.GWT;
import com.google.web.bindery.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.web.bindery.event.shared.HandlerRegistration;
import com.google.web.bindery.event.shared.binder.EventBinder;
import com.google.web.bindery.event.shared.binder.EventHandler;

public class RegisterActivity extends BaseActivity<RegisterView> implements Activity, RegisterView.Presenter{
	interface MyEventBinder extends EventBinder<RegisterActivity> {}
	private static final MyEventBinder eventBinder = GWT.create(MyEventBinder.class);
	
	private EventBus eventBus=null;
	private HandlerRegistration eventRegistration = null;
	
	//private final UserServiceAsync signService = GWT.create(UserService.class);
	
	@Inject
    PlaceControllerHolder placeControllerHolder;
	
	@Inject
	UserService signService;
	
	public boolean registrar(String correo, String contrasena, String nombre, String apellido1, String apellido2){
		/*signService.register(correo, contrasena, nombre, apellido1, apellido2, new AsyncCallback<UsuarioGWT>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Fallo conexion");
				
			}
			
			@Override
			public void onSuccess(UsuarioGWT usuarioGWT) {
				setUsuarioGWT(usuarioGWT);
			}
			
			
			});*/
		this.setUsuarioGWT(this.signService.register(correo, contrasena, nombre, apellido1, apellido2));
		
		return true;
	}
	
	protected boolean setUsuarioGWT(UsuarioGWT usuarioGWT){
		this.eventBus.fireEvent(new UserRegisteredEvent(usuarioGWT));
		return true;
	}

	@Override
	public void startHandler() {
		if(this.eventRegistration==null){
			this.eventRegistration = eventBinder.bindEventHandlers(this, this.eventBus);
		}
	}

	@Override
	public void stopHandler() {
		if(this.eventRegistration!=null){
			eventRegistration.removeHandler();
		}
	}
	
	@EventHandler
	void onShowRegisterForm(UserNotRegisteredEvent event){
		//TODO
	}

	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(AcceptsOneWidget panel) {
		panel.setWidget(getView());
        getView().setPresenter(this);
        this.eventBus = this.placeControllerHolder.getEventBus();
        this.startHandler();
	}
	
}
