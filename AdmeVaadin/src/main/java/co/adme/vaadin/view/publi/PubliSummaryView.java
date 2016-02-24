package co.adme.vaadin.view.publi;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

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
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import co.adme.vaadin.modelo.Modelo;
import co.adme.vaadin.modelo.Owner;
import co.adme.vaadin.modelo.Publi;
import co.adme.vaadin.presenter.PubliPresenter;
import co.adme.vaadin.view.DefaultViewManager;
import co.adme.vaadin.view.SummaryView;

@SuppressWarnings("serial")
public class PubliSummaryView extends SummaryView implements PubliView {
	 	 
	 private Publi publi;
	 
	 private PubliViewListener listener;
	 
	 //Componentes
	 Button adme;
	 Button unadme;
	 
	 @PostConstruct
	 protected void init() {
	 	 super.init();
		 viewManager.configure(this, PubliPresenter.class);
		 
		 adme = new Button("AdMe",this);
		 adme.setData("adme");
		 setComponentOnNavBar(adme);
		 
		 unadme = new Button("UnAdMe",this);
		 unadme.setData("unadme");
		 setComponentOnNavBar(unadme);
     }
	 public Publi getPubli(){
		 return this.publi;
	 }

	@Override
	public void enter(ViewChangeEvent event) {

	}

	@Override
	public void layoutClick(LayoutClickEvent event) {
		UI.getCurrent().getSession().setAttribute(this.getPubli().getId(), this.getPubli());
		UI.getCurrent().getNavigator().navigateTo(PubliCRUDViewImpl.VIEW_NAME+"/"+this.getPubli().getId());
		/*
		PubliDetailWindow publiDetailWindow;
		switch(event.getButton()){
		case LEFT:
			// Call sub-window
			publiDetailWindow = new PubliDetailWindow(publi);
			UI.getCurrent().addWindow(publiDetailWindow);
			break;
			
		case MIDDLE:
			//UI.getCurrent().getNavigator().navigateTo(PubliDetailView.VIEW_NAME);
			//BrowserWindowOpener open = new BrowserWindowOpener(PubliDetailView.VIEW_NAME);

			UI.getCurrent().getPage().open(PubliDetailView.VIEW_NAME, "_blank");
			break;
			
		case RIGHT:
			//PubliEditView publiEditView = new PubliEditView(publi);
			publiDetailWindow = new PubliDetailWindow(publi, PubliDetailWindow.EDIT);
			//publiDetailWindow.setData(publi);
			UI.getCurrent().getSession().setAttribute(Publi.class, publi);
			UI.getCurrent().addWindow(publiDetailWindow);
			break;
		default:
			break;
		}
		*/
	}

	@Override
	public void buttonClick(ClickEvent event) {
		super.buttonClick(event);
		
		String option = (String)event.getButton().getData();
		
		switch(option){
		case "adme":
			if(this.getListener().adme(publi))
				Notification.show("Gracias");
			else
				Notification.show("Ups! Algo ha ido mal");
			break;
		case "unadme":
			if(this.getListener().unadme(publi))
				Notification.show("Gracias");
			else
				Notification.show("Ups! Algo ha ido mal");
			break;
		}
	}

	@Override
	public void addPubliListener(PubliViewListener listener) {
		this.listener = listener;
	}
	
	@Override
	public PubliViewListener getListener() {
		return this.listener;
	}

	@Override
	public void setContent(Modelo publi) {
		if(this.publi==null){
			super.setUp(publi, getListener());
			this.publi = (Publi) publi;
			
			BeanItem<Publi> publiItem = new BeanItem<Publi>(this.getPubli());
			FieldGroup binder = new FieldGroup(publiItem);
			 
			FormLayout formLayout = new FormLayout();
			addComponent(formLayout);
				 
			formLayout.addComponent(binder.buildAndBind(Publi.TITLE_PROPERTYID));
			//formLayout.addComponent(binder.buildAndBind(Publi.CITY_PROPERTYID));
			//formLayout.addComponent(binder.buildAndBind(Publi.DESCRIPTION_PROPERTYID));
			formLayout.addComponent(binder.buildAndBind(Publi.PRICE_PROPERTYID));
			 
			binder.getField(Publi.TITLE_PROPERTYID).setReadOnly(true);
			binder.getField(Publi.PRICE_PROPERTYID).setReadOnly(true);	
		}
		updateAdmeButton(this.publi);
	}
		
	
	@Override
	public void remove(Modelo modelo) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void addWin(Modelo modelo) {
		if(modelo instanceof Publi){
			if(this.publi.equals(publi))
				updateAdmeButton(publi);
		}
	}
	
	private void updateAdmeButton(Publi publi){
		if(this.getListener().isUserSponsor(publi)){
			adme.setVisible(false);
			unadme.setVisible(true);
		}else{
			adme.setVisible(true);
			unadme.setVisible(false);
		}
	}
	
	@Override
	public void removeWin(Modelo modelo) {
		if(modelo instanceof Publi){
			if(this.publi.equals(publi))
				updateAdmeButton(publi);
		}
	}
}
