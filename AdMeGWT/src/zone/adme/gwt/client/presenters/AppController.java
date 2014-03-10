package zone.adme.gwt.client.presenters;

import zone.adme.gwt.client.events.UserNotRegisteredEvent;
import zone.adme.gwt.client.views.MainUI;
import zone.adme.gwt.client.views.RegisterUI;
import zone.adme.gwt.client.views.SignUI;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.web.bindery.event.shared.HandlerRegistration;
import com.google.web.bindery.event.shared.binder.EventBinder;
import com.google.web.bindery.event.shared.binder.EventHandler;

public class AppController implements Presenter {
	interface MyEventBinder extends EventBinder<AppController> {}
	private static final MyEventBinder eventBinder = GWT.create(MyEventBinder.class);
		
	private MainUI  main = null;
	private EventBus eventBus = null;
	private HandlerRegistration eventRegistration = null;
	
	public AppController(MainUI main, EventBus eventBus){
		this.main = main;
		this.eventBus = eventBus;
	}

	@Override
	public boolean bind() {
		// TODO Auto-generated method stub
		return false;
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
	
	public boolean go(){
		SignUI signUI = new SignUI();
		SignPresenter signPresenter = new SignPresenter(signUI,eventBus);
		signPresenter.start();
		main.getNorte().add(signUI);
		RootLayoutPanel.get().add(main);
		
		return true;
	}
	
	@EventHandler
	 void onUserNotRegistered(UserNotRegisteredEvent event) {
	    RegisterUI registerUI = new RegisterUI();
	    RegisterPresenter registerPresenter = new RegisterPresenter(registerUI, eventBus);
	    registerPresenter.start();
	    main.getCentro().add(registerUI);
	 }

}
