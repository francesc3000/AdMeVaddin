package zone.adme.gwt.client.views.interfaces;

import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.IsWidget;

public interface BuscaView extends IsWidget{
	
	void setPresenter(Presenter presenter);
	
	public interface Presenter
	{
		void buscar();
		void goTo(Place place);
		void startHandler();
		void stopHandler();		
	}

}
