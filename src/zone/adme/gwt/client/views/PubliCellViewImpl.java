package zone.adme.gwt.client.views;

import zone.adme.gwt.shared.PubliGWT;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.uibinder.client.UiRenderer;
import com.google.gwt.uibinder.client.UiTemplate;

public class PubliCellViewImpl extends AbstractCell<PubliGWT> {
	
	@UiTemplate("PubliCellView.ui.xml")
	interface MyUiRenderer extends UiRenderer {
		void render(SafeHtmlBuilder sb, PubliGWT publiGWT);
	}
	
	public @UiConstructor PubliCellViewImpl(){
		
	}

	private static MyUiRenderer renderer = GWT.create(MyUiRenderer.class);
	@Override
	//com.google.gwt.cell.client.Cell.Context
	public void render(Context context,
			PubliGWT value, SafeHtmlBuilder sb) {
		// Value can be null, so do a null check..
	    if (value == null) {
	      return;
	    }
		renderer.render(sb, value);	
	}
}
