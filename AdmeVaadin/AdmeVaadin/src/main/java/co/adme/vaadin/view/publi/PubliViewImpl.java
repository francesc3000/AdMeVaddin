package co.adme.vaadin.view.publi;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.spring.annotation.VaadinSessionScope;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.UI;

import co.adme.vaadin.modelo.Modelo;
import co.adme.vaadin.modelo.Publi;
import co.adme.vaadin.view.DefaultViewManager;
import co.adme.vaadin.view.group.GroupSummaryView;
import co.adme.vaadin.view.personalArea.PersonalAreaViewLayout;

@SuppressWarnings("serial")
@UIScope
@SpringView(name = PubliViewImpl.VIEW_NAME)
@VaadinSessionScope
public class PubliViewImpl extends PersonalAreaViewLayout 
						   implements PubliView, ClickListener{
	 public static final String VIEW_NAME = "groupList";
	 
	 @Autowired
	 private DefaultViewManager viewManager;
	 
	 /* Only the presenter registers one listener... */
	 private PubliViewListener listener;
	 
	 @PostConstruct
	 protected void init() {
		 super.init();
		 super.setSummaryViewClass(PubliSummaryView.class);
		 this.viewManager.configure(this);
		 
		 CssLayout navbar = new CssLayout();
		 addComponent(navbar);
		 
		 Button newPubli = new Button("Nueva Publicación");
  	     newPubli.setData("newPubli");
 	     newPubli.addClickListener(this);
 	     navbar.addComponent(newPubli);
 	    
	     Button newCompetition = new Button("Nuevo Evento");
	     newCompetition.setData("newEvent");
	     newCompetition.addClickListener(this);
	     navbar.addComponent(newCompetition);
	   
	     this.setOwnerPanelCaption("Mis Publicaciones");
	     this.setFavoritePanelCaption("Mis Publicaciones Favoritas");
	     this.setWinPanelCaption("Mis Publicaciones Ganadas");
	 }
	 
	@Override
	public void addPubliListener(PubliViewListener listener) {
		this.listener = listener;
	}

	@Override
	public void enter(ViewChangeEvent event) {
		this.setOwnerList(getListener().getMyPubliList());
		this.setFavoriteList(getListener().getFavoriteList());
		this.setWinList(getListener().getWinPubliList());
	   
		super.enter(event);
	}
	
	public void load(){	     
		this.setOwnerList(getListener().getMyPubliList());
		this.setFavoriteList(getListener().getFavoriteList());
		this.setWinList(getListener().getWinPubliList());
		
		super.load();
	}

	@Override
	public void buttonClick(ClickEvent event) {
		
		String option = (String) event.getButton().getData();
		switch(option){
		case "newPubli":
			UI.getCurrent().getNavigator().navigateTo(PubliCRUDViewImpl.VIEW_NAME+"/"+PubliCRUDViewImpl.NEW_PUBLI);
			break;
		case "newEvent":
			UI.getCurrent().getNavigator().navigateTo(PubliCRUDViewImpl.VIEW_NAME+"/"+PubliCRUDViewImpl.NEW_PUBLI);
			break;
		}
	}

	@Override
	public PubliViewListener getListener() {
		return this.listener;
	}
}