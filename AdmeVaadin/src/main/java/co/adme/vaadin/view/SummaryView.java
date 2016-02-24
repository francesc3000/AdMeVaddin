package co.adme.vaadin.view;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.teemu.ratingstars.RatingStars;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.event.LayoutEvents.LayoutClickEvent;
import com.vaadin.event.LayoutEvents.LayoutClickListener;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.spring.annotation.VaadinSessionScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import co.adme.vaadin.modelo.Modelo;
import co.adme.vaadin.modelo.Publi;
import co.adme.vaadin.presenter.PubliPresenter;
import co.adme.vaadin.view.DefaultViewManager;
import co.adme.vaadin.view.publi.PubliView.PubliViewListener;

@SuppressWarnings("serial")
@UIScope
@SpringView(name = SummaryView.VIEW_NAME)
@VaadinSessionScope
public abstract class SummaryView extends VerticalLayout implements View,
																	ModeloView,
																	LayoutClickListener,
																    ClickListener {
	 public static final String VIEW_NAME = "summary";
	 
	 @Autowired
	 protected DefaultViewManager viewManager;
	 
	 private Modelo modelo;
	 
	 //Componentes
	 private CssLayout navbar;
	 private Button favButton;
	 private Button unfavButton;
	 
	 private ModeloViewListener listener;
	 
	 protected SummaryView(){}
	 
	 @PostConstruct
	 protected void init() {
		 addLayoutClickListener(this);
		 setWidth("200");
		 //setHeight("300");
		 
		 HorizontalLayout navHorizontal = new HorizontalLayout();
		 addComponent(navHorizontal);
		 navbar = new CssLayout();
		 navHorizontal.addComponent(navbar);
		 
		 
	     favButton = new Button("Favorito");
		 favButton.setData("favorite");
		 favButton.addClickListener(this);
		 setComponentOnNavBar(favButton);
		 
		 unfavButton = new Button("No Favorito");
		 unfavButton.setData("unfavorite");
		 unfavButton.addClickListener(this);
		 setComponentOnNavBar(unfavButton);
    }

	@Override
	public void enter(ViewChangeEvent event) {

	}

	@Override
	public void layoutClick(LayoutClickEvent event) {
	}

	@Override
	public void buttonClick(ClickEvent event) {
		String option = (String)event.getButton().getData();
		
		switch(option){
		case "favorite":
			this.listener.addFavorite(this.modelo);
			this.favButton.setVisible(false);
			this.unfavButton.setVisible(true);
			break;
		case "unfavorite":
			this.listener.removeFavorite(this.modelo);
			this.favButton.setVisible(true);
			this.unfavButton.setVisible(false);
			
		default:
		}
	}

	public void setUp(Modelo modelo, ModeloViewListener listener) {			
		this.modelo = modelo;
		this.listener = listener;
		if(listener.getFavoriteList().contains(modelo)){
			this.favButton.setVisible(false);
			this.unfavButton.setVisible(true);
		}
		else{
			this.favButton.setVisible(true);
			this.unfavButton.setVisible(false);
		}
		
		RatingStars rating = new RatingStars();
		rating.setValue(modelo.getRating());
		rating.setEnabled(false);
		addComponent(rating);
	}
	
	protected void setComponentOnNavBar(Component component){
		this.navbar.addComponent(component);
	}
	
	public Modelo getSummary(){
		return this.modelo;
	}

	@Override
	public void addFavorite(Modelo modelo) {
		if(this.modelo.equals(modelo)){
			this.favButton.setVisible(false);
			this.unfavButton.setVisible(true);
		}
	}

	@Override
	public void removeFavorite(Modelo modelo) {
		if(this.modelo.equals(modelo)){
			this.favButton.setVisible(true);
			this.unfavButton.setVisible(false);
		}
	}
}
