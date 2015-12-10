package co.adme.vaadin.view.search;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

import co.adme.vaadin.modelo.Publi;


public class PubliDetailWindow extends Window {
	 public PubliDetailWindow(Publi publi) {
	        super(publi.getTitle()); // Set window caption
	        center();

	        // Some basic content for the window
	        VerticalLayout content = new VerticalLayout();
	        content.addComponent(new Label("Soy el puto amo!!"));
	        content.setMargin(true);
	        setContent(content);
	        
	        // Disable the close button
	        setClosable(false);

	        // Trivial logic for closing the sub-window
	        Button ok = new Button("OK");
	        ok.addClickListener(new ClickListener() {

				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(com.vaadin.ui.Button.ClickEvent event) {
					close(); // Close the sub-window
					
				}

	        });
	        content.addComponent(ok);
	    }
}
