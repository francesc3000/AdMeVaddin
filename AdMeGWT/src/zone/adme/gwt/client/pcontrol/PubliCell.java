package zone.adme.gwt.client.pcontrol;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiRenderer;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;

public class PubliCell extends AbstractCell<PubliGWT> {

	interface MyUiRenderer extends UiRenderer {
		void render(SafeHtmlBuilder sb, PubliGWT publiGWT);
	}
	
	@UiField
	TextBox nombrePubli;
	
	private static MyUiRenderer renderer = GWT.create(MyUiRenderer.class);
	@Override
	public void render(com.google.gwt.cell.client.Cell.Context context,
			PubliGWT value, SafeHtmlBuilder sb) {
		renderer.render(sb, value);		
	}
}
