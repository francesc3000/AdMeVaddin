package co.adme.vaadin;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.vaadin.spring.events.EventBus;
import org.vaadin.spring.events.EventBusListener;
import org.vaadin.spring.events.EventScope;
import org.vaadin.spring.events.annotation.EventBusListenerMethod;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.Widgetset;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.Button;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;

import co.adme.vaadin.modelo.Publi;


@SuppressWarnings("serial")
@Theme("valo")
@SpringUI
@Title(value = "AdMe")
@Widgetset("AppWidgetset")
public class AdMeVaadinUI extends UI {
	
	@Autowired
	SpringViewProvider viewProvider;
	
	@Autowired
    private ApplicationContext applicationContext;
	
	@Inject
    //EventBus.UIEventBus eventBus;
	EventBus.UIEventBus eventBus;
	
	private final Logger log = LoggerFactory.getLogger(AdMeVaadinUI.class);
	 
    @Override
    protected void init(VaadinRequest vaadinRequest) {    	
    	final Panel mainContainer = new Panel();
    	mainContainer.setSizeFull();
        setContent(mainContainer);
        
        Navigator navigator = new Navigator(this, mainContainer);
        navigator.addProvider(viewProvider);
        
        eventBus.subscribe(this);

        /*
        setContent(new Button("Create event", (e) -> {
        	Event event = new Event(this);
            eventBus.publish(EventScope.UI, this, event);
        }));
        */
        
        /*
         * Debido a que no tengo ni idea como instanciar clases del tipo
         * modelo dentro del contexto utilizando el framework de Spring
         * se debe hacer este workaround. Se introduce el appcontext en 
         * la session y se recupera a la hora de crear las clases modelo 
         * a través de las llamadas a MongoDB
         */
        UI.getCurrent().getSession().setAttribute(ApplicationContext.class, applicationContext);
        
        //UI.getCurrent().getNavigator().navigateTo(MainViewImpl.VIEW_NAME);
    }    
    
    @EventBusListenerMethod
    public void onEvent(String str) {
        log.debug("Event received: {}", str);
    }
}