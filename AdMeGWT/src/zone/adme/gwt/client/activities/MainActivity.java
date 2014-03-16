package zone.adme.gwt.client.activities;

import zone.adme.gwt.client.ClientFactory;
import zone.adme.gwt.client.places.MainPlace;
import zone.adme.gwt.client.places.SignPlace;
import zone.adme.gwt.client.views.interfaces.MainView;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.web.bindery.event.shared.HandlerRegistration;
import com.google.web.bindery.event.shared.binder.EventBinder;

public class MainActivity extends AbstractActivity implements MainView.Presenter{
	interface MyEventBinder extends EventBinder<MainActivity> {}
	private static final MyEventBinder eventBinder = GWT.create(MyEventBinder.class);
	
	private HandlerRegistration eventRegistration = null;
	private ClientFactory clientFactory;
	
	private SignActivity signActivity = null;
	
	public MainActivity(MainPlace place, ClientFactory clientFactory){
		this.clientFactory = clientFactory;	
		this.signActivity = new SignActivity(clientFactory);
		this.clientFactory.getSignView().setPresenter(signActivity);
		this.clientFactory.getMainView().getNorte().add(this.clientFactory.getSignView());
	}
	
	@UiField
	Label buscaLbl;
	@UiField
	TextBox buscaTBox;
	@UiField
	Button buscaBtn;

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		MainView mainView = this.clientFactory.getMainView();
		mainView.setPresenter(this);
		panel.setWidget(mainView.asWidget());
		
	}

	@Override
	public void goTo(Place place) {
		clientFactory.getPlaceController().goTo(place);
	}

	@Override
	public void startHandler() {
		if(this.eventRegistration==null){
			this.eventRegistration = eventBinder.bindEventHandlers(this, this.clientFactory.getEventBus());
		}		
	}

	@Override
	public void stopHandler() {
		if(this.eventRegistration!=null){
			eventRegistration.removeHandler();
		}
		
	}

}
