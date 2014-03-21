package zone.adme.gwt.client.views.interfaces;

import com.google.gwt.user.client.ui.IsWidget;

import zone.adme.gwt.client.activities.RegisterActivity;

public interface RegisterView extends IsWidget{
	void clear();
	void setPresenter(RegisterActivity presenter);
	
	public interface Presenter
	{
		void startHandler();
		void stopHandler();
	}
}
