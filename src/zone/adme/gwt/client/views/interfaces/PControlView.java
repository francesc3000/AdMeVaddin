package zone.adme.gwt.client.views.interfaces;

import zone.adme.gwt.shared.PubliGWT;

import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.client.ui.IsWidget;

public interface PControlView extends IsWidget{
	void clear();
	void setPresenter(Presenter presenter);
	void setCellList(CellList<PubliGWT> cellList);
	
	public interface Presenter
	{
		void startHandler();
		void stopHandler();
		boolean OpenCellListClick();
	}
}
