package zone.adme.gwt.client.pcontrol;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.cell.client.Cell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

public class PControlUI extends Composite implements HasText {

	private static PControlUIUiBinder uiBinder = GWT
			.create(PControlUIUiBinder.class);

	interface PControlUIUiBinder extends UiBinder<Widget, PControlUI> {
	}

	public PControlUI() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@UiField
	Button button;

	public PControlUI(String firstName) {
		initWidget(uiBinder.createAndBindUi(this));
		button.setText(firstName);
		
	}

	@UiHandler("button")
	void onClick(ClickEvent e) {
		PubliCell publiCell = new PubliCell();
		CellList<PubliGWT> cellList = new CellList<PubliGWT>(publiCell);
		List<PubliGWT> lista = new ArrayList<PubliGWT>();
		
		PubliGWT p1 = new PubliGWT();
		
		p1.setUsuario("Alberto");
		p1.setTitulo("TITULO 1");
		p1.setTexto("Esta es la publicacion numero 1");
		
		PubliGWT p2 = new PubliGWT();
		
		p2.setUsuario("Alfonso Javier");
		p2.setTitulo("TITULO 2");
		p2.setTexto("Esta es la publicacion numero 2, iauygp aui apuihgpa aui gauerp ahit ");
		
		lista.add(p1);
		lista.add(p2);
		
		cellList.setRowCount(lista.size(),true);
		cellList.setRowData(lista);
		RootPanel.get().add(cellList);
		
		Window.alert("Hello!");
	}

	public void setText(String text) {
		button.setText(text);
	}

	public String getText() {
		return button.getText();
	}

}
