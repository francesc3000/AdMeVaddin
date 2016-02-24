package co.adme.vaadin.view.publi;

import javax.annotation.PostConstruct;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.spring.annotation.VaadinSessionScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

import co.adme.vaadin.modelo.Publi;

@SuppressWarnings("serial")
@UIScope
@VaadinSessionScope
public class SnippetWindowOK extends Window implements View{
	 
	 public SnippetWindowOK(Publi publi){
		 super(publi.getTitle()); // Set window caption
		 init();
	 }
	 
	 @PostConstruct
	 void init() {
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
	 /*
	public void setPubliDetails(Publi publi){
		this.setCaption(publi.getTitle());
	}
*/
	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		
	}
}
