package zone.adme.gwt.client.activities;

import java.util.List;

import javax.inject.Inject;

import zone.adme.core.modelo.Mensaje;
import zone.adme.gwt.client.mapper.PlaceControllerHolder;
import zone.adme.gwt.client.services.PubliService;
import zone.adme.gwt.client.services.PubliServiceAsync;
import zone.adme.gwt.client.services.UserService;
import zone.adme.gwt.client.services.UserServiceAsync;
import zone.adme.gwt.client.views.interfaces.FormPubliView;
import zone.adme.gwt.shared.PubliGWT;
import zone.adme.gwt.shared.UsuarioGWT;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.web.bindery.event.shared.HandlerRegistration;
import com.google.web.bindery.event.shared.binder.EventBinder;

public class FormPubliActivity extends BaseActivity<FormPubliView> implements FormPubliView.Presenter{
	interface MyEventBinder extends EventBinder<FormPubliActivity> {}
	private static final MyEventBinder eventBinder = GWT.create(MyEventBinder.class);
	
	private HandlerRegistration eventRegistration = null;
	private final PubliServiceAsync publiService = GWT.create(PubliService.class);
	private final UserServiceAsync userService = GWT.create(UserService.class);
	
	private UsuarioGWT usuarioGWT = null;
	
	@Inject
    PlaceControllerHolder placeControllerHolder;
	
	private void getUsuarioGWTSession(){
		userService.getUsuarioGWTSession(new AsyncCallback<UsuarioGWT>(){

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(UsuarioGWT result) {
				usuarioGWT = result;
			}
			
		});
	}

	@Override
	public void startHandler() {
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
	public void onStop() {
		this.stopHandler();
	}

	@Override
	public void onStart(AcceptsOneWidget panel) {
		panel.setWidget(getView());
        getView().setPresenter(this);
        this.startHandler();
        this.getUsuarioGWTSession();
	}

	@Override
	public void crearPubli(String titulo, String descripcion) {
		
		PubliGWT publiGWT = new PubliGWT();
		
		publiGWT.setUsuarioGWT(this.usuarioGWT);
		publiGWT.setTexto(titulo);
		publiGWT.setTitulo(descripcion);
		
		this.publiService.putPubliGWT(publiGWT, new AsyncCallback<List<Mensaje>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Fallo conexion");
				
			}

			@Override
			public void onSuccess(List<Mensaje> result) {
				// TODO Auto-generated method stub
				
			}
			
			
		});
		
		
	}
}
