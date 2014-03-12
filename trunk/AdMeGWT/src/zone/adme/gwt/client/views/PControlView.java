package zone.adme.gwt.client.views;

import com.google.gwt.user.client.ui.IsWidget;

public interface PControlView extends IsWidget{
	void clear();
	void setPresenter(Presenter presenter);
	
	public interface Presenter
	{
		boolean start();
		boolean stop();
		boolean OpenCellListClick();
	}
}
