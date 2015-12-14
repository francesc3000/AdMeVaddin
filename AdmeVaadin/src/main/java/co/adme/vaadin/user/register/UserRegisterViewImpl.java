package co.adme.vaadin.user.register;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.spring.annotation.VaadinSessionScope;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.themes.ValoTheme;

import co.adme.vaadin.modelo.Publi;
import co.adme.vaadin.view.DefaultViewManager;

@SuppressWarnings("serial")
@UIScope
@SpringView(name = UserRegisterViewImpl.VIEW_NAME)
@VaadinSessionScope
public class UserRegisterViewImpl extends VerticalLayout 
implements View, UserRegisterView, ItemClickListener, ClickListener{




	public static final String VIEW_NAME = "register";

	@Autowired
	private DefaultViewManager viewManager;


	private HorizontalLayout horizontal;
	
	private TextField searchTerm;
	private TextField searchCity;


	private Table table;	
	/* Only the presenter registers one listener... */
	private List<UserRegisterListener> listeners =
			new ArrayList<UserRegisterListener>();

	@PostConstruct
	void init() {
		setMargin(true);
		setSpacing(true);

	    horizontal = new HorizontalLayout();
        horizontal.setWidth("100%");
		addComponent(horizontal);
		
		final CssLayout navigationBar = new CssLayout();
		navigationBar.addStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
		horizontal.addComponent(navigationBar);
		
		searchTerm = new TextField( );
		searchTerm.setInputPrompt("HOLA");
		navigationBar.addComponent(searchTerm);
		
		searchCity = new TextField( );
		searchCity.setValue("REGISTRO");
		searchCity.setEnabled(false);
		navigationBar.addComponent(searchCity);
		
	    //Se configuran las views con sus presenters
	    viewManager.configure(this);
		
	}
		
		
		
		
		@Override
		public void buttonClick(ClickEvent event) {

			for(UserRegisterListener listener: listeners){
				//listener.searchPubliList(searchTerm.getValue());
				
				UI.getCurrent().getNavigator().navigateTo((String)event.getButton().getData());
			}

		}

		@Override
		public void itemClick(ItemClickEvent event) {
			// TODO Auto-generated method stub

		}


		@Override
		public void enter(ViewChangeEvent event) {
			// TODO Auto-generated method stub

		}


		@Override
		public void addUserRegisterListener(UserRegisterListener listener) {
			// TODO Auto-generated method stub

		}

	}
