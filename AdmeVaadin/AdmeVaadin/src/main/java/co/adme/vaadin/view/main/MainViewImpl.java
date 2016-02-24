package co.adme.vaadin.view.main;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.annotations.Theme;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.spring.annotation.VaadinSessionScope;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.VerticalLayout;

import co.adme.vaadin.modelo.User;
import co.adme.vaadin.user.register.UserRegisterViewImpl;
import co.adme.vaadin.view.DefaultViewManager;
import co.adme.vaadin.view.personalArea.PersonalAreaViewImpl;
import co.adme.vaadin.view.podium.AdPodiumImpl;
import co.adme.vaadin.view.search.SearchViewImpl;

@SuppressWarnings("serial")
@UIScope
@Theme("valo")
@SpringView(name = MainViewImpl.VIEW_NAME)
@VaadinSessionScope
public class MainViewImpl extends VerticalLayout implements View, MainView, 
															ClickListener{
	public static final String VIEW_NAME = "";
	
	@Autowired
	private DefaultViewManager viewManager;
	
	@Autowired
	SearchViewImpl searchViewImpl;
	
	@Autowired
	AdPodiumImpl adPodiumImpl;

	private MainViewListener listener;
	
    @PostConstruct
    void init() {
        setMargin(true);
        setSpacing(true);
        //setSizeUndefined();
        setSizeFull();
        
        viewManager.configure(this);
        
        GridLayout gridLayout = new GridLayout(1,3);
        //gridLayout.setSizeUndefined();
        gridLayout.setSizeFull();
        //gridLayout.setHeight("200px");
        addComponent(gridLayout);
        
        HorizontalLayout top = new HorizontalLayout();
        top.setSizeFull();
        //top.setSizeUndefined();
        
        CssLayout navbar = new CssLayout();
        top.addComponent(navbar);
        top.setComponentAlignment(navbar, Alignment.TOP_RIGHT);
 
        Button userButton = new Button("Registrarse");
    	userButton.addClickListener(this);
    	userButton.setData(UserRegisterViewImpl.VIEW_NAME);
    	navbar.addComponent(userButton);
    	
    	
    	Button personalArea = new Button("Area Personal");
    	personalArea.addClickListener(this);
    	personalArea.setData(PersonalAreaViewImpl.VIEW_NAME);
    	navbar.addComponent(personalArea);
        
    	HorizontalLayout middle = new HorizontalLayout();
    	middle.setSizeFull();
    	//middle.addComponent(searchViewImpl);
    	
    	CssLayout middleContent = new CssLayout();
    	middleContent.addComponent(searchViewImpl);
    	middleContent.setSizeUndefined();
    	middle.addComponent(middleContent);
    	middle.setComponentAlignment(middleContent, Alignment.TOP_CENTER);  
    	
        
    	HorizontalLayout bottom = new HorizontalLayout();
        bottom.setSizeUndefined();
        bottom.addComponent(adPodiumImpl);
        
        gridLayout.addComponent(top,0,0);
        gridLayout.addComponent(middle,0,1);
        //gridLayout.addComponent(bottom,0,2);
        
        gridLayout.setRowExpandRatio(0, 1000.0f);
        gridLayout.setRowExpandRatio(1, 100000.0f);
        gridLayout.setRowExpandRatio(2, 10.0f);

	}

	@Override
	public void buttonClick(ClickEvent event) {
		UI.getCurrent().getNavigator().navigateTo((String)event.getButton().getData());
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addMainListener(MainViewListener listener) {
		this.listener = listener;
	}
}