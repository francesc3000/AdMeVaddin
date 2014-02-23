package zone.adme.gwt.client.pcontrol;

import zone.adme.gwt.shared.PubliGWT;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.uibinder.client.UiRenderer;

public class PubliCell extends AbstractCell<PubliGWT> {

	interface MyUiRenderer extends UiRenderer {
		void render(SafeHtmlBuilder sb, PubliGWT publiGWT);
	}

	private static MyUiRenderer renderer = GWT.create(MyUiRenderer.class);
	@Override
	public void render(com.google.gwt.cell.client.Cell.Context context,
			PubliGWT value, SafeHtmlBuilder sb) {
		// Value can be null, so do a null check..
	    if (value == null) {
	      return;
	    }
		renderer.render(sb, value);	
	}
}
