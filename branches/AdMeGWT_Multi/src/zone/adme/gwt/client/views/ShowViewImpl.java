package zone.adme.gwt.client.views;

import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import zone.adme.gwt.client.views.interfaces.ShowView;
import zone.adme.gwt.shared.PubliGWT;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Widget;

public class ShowViewImpl extends Composite implements ShowView { //ProvidesResize {

	private static ShowViewImplUiBinder uiBinder = GWT.create(ShowViewImplUiBinder.class);
	
	private Presenter presenter = null;
	
	/*@UiField
	CellList<PubliGWT> cellList;
	*/
	@UiField
	FlexTable flexTable;
	
	@UiTemplate("ShowView.ui.xml")
	interface ShowViewImplUiBinder extends UiBinder<Widget, ShowViewImpl> {
	}

	@Inject
	public ShowViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	/*
	@UiFactory
	PubliCellViewImpl buildPubliCell(){
		return new PubliCellViewImpl();
	}
	*/
	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}
	
	@Override
	public void setFlexTable(List<PubliGWT> listaPubliGWT){
		
		flexTable.setText(0, 0, "Usuario");  
		flexTable.setText(0, 1, "Titulo");  
		flexTable.setText(0, 2, "Publicacion");
		flexTable.getRowFormatter().addStyleName(0, "watchListHeader");

		Iterator<PubliGWT> iter = listaPubliGWT.iterator();
		int i=1;


		while (iter.hasNext()){

			PubliGWT publi = (PubliGWT) iter.next();
			//flexTable.setText(i, 0,publi.getUsuarioGWT().getNombre() );
			flexTable.setText(i, 1,publi.getTitulo() );
			flexTable.setText(i, 2,publi.getTexto());
			i++;
				
		}
	}
	
	@Override
	public void setCellTable(CellList<PubliGWT> cellList) {
		//this.cellList = cellList;
	}
}
