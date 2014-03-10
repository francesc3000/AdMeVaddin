package zone.adme.gwt.client.presenters;

import zone.adme.gwt.client.events.UserNotRegisteredEvent;
import zone.adme.gwt.client.events.UserRegisteredEvent;
import zone.adme.gwt.client.services.UserService;
import zone.adme.gwt.client.services.UserServiceAsync;
import zone.adme.gwt.shared.UsuarioGWT;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.event.shared.HandlerRegistration;
import com.google.web.bindery.event.shared.binder.EventBinder;
import com.google.web.bindery.event.shared.binder.EventHandler;

public class RegisterPresenter implements Presenter{
	interface MyEventBinder extends EventBinder<RegisterPresenter> {}
	private static final MyEventBinder eventBinder = GWT.create(MyEventBinder.class);
	
	private EventBus eventBus=null;
	private HandlerRegistration eventRegistration = null;
	
	private Display view = null;
	
	private final UserServiceAsync signService = GWT.create(UserService.class);
	
	public interface Display{
		public void clear();
		public Widget asWidget();
		public boolean setPresenter(RegisterPresenter presenter);
	}
	
	public RegisterPresenter(Display view, EventBus eventBus){
		this.eventBus = eventBus;
		this.view = view;
	}
	
	public boolean registrar(String correo, String contrasena, String nombre, String apellido1, String apellido2){
		signService.register(correo, contrasena, nombre, apellido1, apellido2, new AsyncCallback<UsuarioGWT>() {

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
	
	protected boolean setUsuarioGWT(UsuarioGWT usuarioGWT){
		this.eventBus.fireEvent(new UserRegisteredEvent(usuarioGWT));
		return true;
	}

	@Override
	public boolean bind() {
		this.view.setPresenter(this);
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
	
	@EventHandler
	void onShowRegisterForm(UserNotRegisteredEvent event){
		//TODO
	}
	
}
