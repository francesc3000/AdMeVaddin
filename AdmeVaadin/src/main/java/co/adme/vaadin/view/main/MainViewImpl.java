package co.adme.vaadin.view.main;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.annotations.Theme;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.spring.annotation.VaadinSessionScope;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.VerticalLayout;

import co.adme.vaadin.view.DefaultViewManager;
import co.adme.vaadin.view.podium.AdPodiumImpl;
import co.adme.vaadin.view.search.SearchViewImpl;

@SuppressWarnings("serial")
@UIScope
@Theme("valo")
@SpringView(name = MainViewImpl.VIEW_NAME)
@VaadinSessionScope
public class MainViewImpl extends VerticalLayout implements View, MainView, 
															ViewChangeListener, ClickListener{
	public static final String VIEW_NAME = "";
	
	@Autowired
	private DefaultViewManager viewManager;
	
	@Autowired
	SearchViewImpl searchViewImpl;
	
	@Autowired
	AdPodiumImpl adPodiumImpl;
	
	/* Only the presenter registers one listener... */
	private List<MainViewListener> listeners =
	        	new ArrayList<MainViewListener>();	
	
    @PostConstruct
    void init() {
        setSizeFull();
        setMargin(true);
        setSpacing(true);
        setSizeUndefined();
        
        HorizontalLayout top = new HorizontalLayout();
    	Button userButton = new Button("Usuario");
    	userButton.addClickListener(this);
    	//userButton.setData(UserViewImpl.VIEWNAME);
    	top.addComponent(userButton);
    	top.setComponentAlignment(userButton, Alignment.MIDDLE_RIGHT);
    	
        
    	HorizontalLayout middle = new HorizontalLayout();
    	middle.addComponent(searchViewImpl);
        
        HorizontalLayout bottom = new HorizontalLayout();
        bottom.addComponent(adPodiumImpl);
        
        addComponent(top);
        addComponent(middle);
        addComponent(bottom);

        viewManager.configure(this);
	}

    @Override
    public void enter(ViewChangeEvent event) {
        // the view is constructed in the init() method()
    }

	@Override
	public boolean beforeViewChange(ViewChangeEvent event) {
		return false;
	}

	@Override
	public void afterViewChange(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		if(event.getParameters() != null) {

		 }
	}

	@Override
	public void buttonClick(ClickEvent event) {
		UI.getCurrent().getNavigator().navigateTo((String)event.getButton().getData());
	}


	@Override
	public void addMainListener(MainViewListener listener) {
		listeners.add(listener);
	}
}
