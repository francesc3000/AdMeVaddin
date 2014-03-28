package zone.adme.gwt.client.views.interfaces;

import java.util.List;

import zone.adme.gwt.shared.PubliGWT;

import com.google.gwt.place.shared.Place;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.IsWidget;

public interface ShowView extends IsWidget{
	
	public interface Presenter
	{
		void setTableData(List<PubliGWT> listPubliGWT);
		void goTo(Place place);
		void startHandler();
		void stopHandler();		
	}
	
	void setCellTable(CellList<PubliGWT> cellList);
	void setPresenter(Presenter presenter);
	void setFlexTable(List<PubliGWT> listaPubliGWT);

}
