package zone.adme.gwt.client.views.interfaces;

import com.google.gwt.user.client.ui.IsWidget;

public interface PControlView extends IsWidget{
	void clear();
	void setPresenter(Presenter presenter);
	
	public interface Presenter
	{
		void startHandler();
		void stopHandler();
		boolean OpenCellListClick();
	}
}
