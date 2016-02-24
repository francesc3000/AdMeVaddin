package co.adme.vaadin.view.podium;

import java.io.File;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.virkki.carousel.HorizontalCarousel;
import org.vaadin.virkki.carousel.client.widget.gwt.ArrowKeysMode;
import org.vaadin.virkki.carousel.client.widget.gwt.CarouselLoadMode;

import com.vaadin.annotations.Theme;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FileResource;
import com.vaadin.server.VaadinService;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.spring.annotation.VaadinSessionScope;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

import com.vaadin.ui.Image;
import com.vaadin.ui.VerticalLayout;

import co.adme.vaadin.view.DefaultViewManager;
import co.adme.vaadin.view.podium.AdPodiumView;

@SuppressWarnings("serial")
@UIScope
@Theme("valo")
@SpringView(name = AdPodiumImpl.VIEW_NAME)
@VaadinSessionScope
public class AdPodiumImpl extends VerticalLayout implements View, AdPodiumView, ClickListener{
	public static final String VIEW_NAME = "adpodium";
	
	@Autowired
	private DefaultViewManager viewManager;
	
	@PostConstruct
    void init() {
        setSizeFull();
        setMargin(true);
        setSpacing(true);
        
        viewManager.configure(this);
        
	    HorizontalCarousel podium = new HorizontalCarousel();
	    // Only react to arrow keys when focused
	    podium.setArrowKeysMode(ArrowKeysMode.FOCUS);
	    // Fetch children lazily
	    podium.setLoadMode(CarouselLoadMode.EAGER);
	    // Transition animations between the children run 500 milliseconds
	    podium.setTransitionDuration(500);
        // Find the application directory
        String basepath = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();

        // Image as a file resource
        FileResource resource = new FileResource(new File(basepath +
                                "/images/yate.jpg"));
        
        Image image = new Image(null,resource);
        image.setWidth(7,Unit.CM);
        image.setHeight(5, Unit.CM);
        podium.addComponent(image);
        
     // Image as a file resource
        resource = new FileResource(new File(basepath +
                                "/images/mujer.jpg"));
        
        
        image = new Image(null,resource);
        image.setWidth(7,Unit.CM);
        image.setHeight(5, Unit.CM);
        podium.addComponent(image);
        
     // Image as a file resource
        resource = new FileResource(new File(basepath +
                                "/images/fiesta.jpg"));
        
        
        image = new Image(null,resource);
        image.setWidth(7,Unit.CM);
        image.setHeight(5, Unit.CM);
        podium.addComponent(image);
        
        addComponent(podium);
        
	}

	@Override
	public void buttonClick(ClickEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		
	}

}
