package zone.adme.gwt.client.presenters;

import zone.adme.gwt.client.events.PControlClickedEvent;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.event.shared.HandlerRegistration;
import com.google.web.bindery.event.shared.binder.EventBinder;
import com.google.web.bindery.event.shared.binder.EventHandler;

public class PControlPresenter implements Presenter{
	interface MyEventBinder extends EventBinder<PControlPresenter> {}
	private static final MyEventBinder eventBinder = GWT.create(MyEventBinder.class);
	
	private EventBus eventBus=null;
	private HandlerRegistration eventRegistration = null;
	
	private Display view = null;
	
	public interface Display{
		public void clear();
		public Widget asWidget();
		public boolean setPresenter(PControlPresenter presenter);
	}
	
	public PControlPresenter(Display view, EventBus eventBus){
		this.eventBus = eventBus;
		this.view = view;
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
	void onShowPControl(PControlClickedEvent event){

	}
	
}
