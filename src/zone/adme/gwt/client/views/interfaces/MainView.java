package zone.adme.gwt.client.views.interfaces;

import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.IsWidget;

public interface MainView extends IsWidget{
	
	FlowPanel getNorte();
	FlowPanel getCentro();
	void setPresenter(Presenter presenter);
	
	public interface Presenter
	{
		void goTo(Place place);
		void startHandler();
		void stopHandler();		
	}

}
