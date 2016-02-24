package co.adme.vaadin.view.personalArea;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.vaadin.LazyList;
import org.vaadin.addons.scrollablepanel.ScrollablePanel;
import org.vaadin.miki.itemgrid.ItemGrid;
import org.vaadin.virkki.carousel.HorizontalCarousel;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.spring.annotation.VaadinSessionScope;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import co.adme.vaadin.modelo.Modelo;
import co.adme.vaadin.modelo.Publi;
import co.adme.vaadin.view.DefaultViewManager;
import co.adme.vaadin.view.DefaultWindow;
import co.adme.vaadin.view.ModeloView;
import co.adme.vaadin.view.SummaryView;
import co.adme.vaadin.view.publi.PubliSummaryView;

@SuppressWarnings("serial")
public abstract class PersonalAreaViewLayout extends VerticalLayout
											  implements View, ModeloView{
	 
	 @Autowired
	 private ApplicationContext applicationContext;
	 
	 private HorizontalLayout myZone;
	 private HorizontalLayout favoritesZone;
	 private HorizontalLayout winZone;
	 
	 private Panel ownerPanel;
	 private Panel favoritesPanel;
	 private Panel winPanel;
	 
	 private List<Modelo> ownerList = new ArrayList<Modelo>();
	 private List<Modelo> favoriteList = new ArrayList<Modelo>();
	 private List<Modelo> winList = new ArrayList<Modelo>();
	 
	 private Class<SummaryView> summaryViewClass;
	 
	 protected PersonalAreaViewLayout(){}
	 
	 @PostConstruct
	 protected void init() {
	     ownerPanel = new Panel();
	     addComponent(ownerPanel);
	     //setExpandRatio(ownerPanel, 1.0f);
	    
	     favoritesPanel = new Panel();
	     addComponent(favoritesPanel);
	    
	     winPanel = new Panel();
	     addComponent(winPanel);
	 }
	 
	 public void setOwnerPanelCaption(String caption){
		 this.ownerPanel.setCaption(caption);
	 }
	 
	 public void setFavoritePanelCaption(String caption){
		 this.favoritesPanel.setCaption(caption);
	 }
	 
	 public void setWinPanelCaption(String caption){
		 this.winPanel.setCaption(caption);
	 }
	 
	 @SuppressWarnings("unchecked")
	public void setSummaryViewClass(Class<?> summaryViewClass){
		 this.summaryViewClass = (Class<SummaryView>) summaryViewClass;
	 }
	 
	 protected void setOwnerList(List<Modelo> ownerList){
		 this.ownerList = ownerList;
	 }
	 
	 protected void setFavoriteList(List<Modelo> favoriteList){
		 this.favoriteList = favoriteList;
	 }
	 
	 protected void setWinList(List<Modelo> winList){
		 this.winList = winList;
	 }
	
	 private Component getSummaryView(Modelo modelo){
	 	Panel publiPanel = new Panel();
		publiPanel.setSizeUndefined();
		
		AutowireCapableBeanFactory factory = applicationContext.getAutowireCapableBeanFactory();
		SummaryView summaryView;

		try{
			summaryView = factory.createBean(summaryViewClass);
			summaryView.setContent(modelo);
			   
		    publiPanel.setContent(summaryView);
	 	}catch(Exception e){
	 		String msg = e.getMessage();
	 		Notification.show(msg);
	 	}
		
	    return publiPanel;
	}
	
	private HorizontalLayout buildZone(List<Modelo> modeloList){
		HorizontalLayout publiZone = new HorizontalLayout(); 
	    publiZone.setSizeUndefined();

	    for(Modelo modelo: modeloList){
	    	publiZone.addComponent(getSummaryView(modelo));
	    }
	    
	    return publiZone;
	}

	@Override
	public void enter(ViewChangeEvent event) {
		load();
	}
	
	public void load(){	     
	    this.myZone = this.buildZone(ownerList);
	    this.favoritesZone = this.buildZone(favoriteList);
	    this.winZone = this.buildZone(winList);
	    
	    ownerPanel.setContent(this.myZone);
	    favoritesPanel.setContent(this.favoritesZone);
	    winPanel.setContent(this.winZone);
	}
	
	@Override
	public void setContent(Modelo modelo) {
		myZone.addComponent(this.getSummaryView(modelo));
	}
	
	@Override
	public void remove(Modelo modelo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addFavorite(Modelo modelo) {
		boolean insert=true;
		int index=0;
		while(index<this.favoritesZone.getComponentCount()){
			Panel panel = (Panel) this.favoritesZone.getComponent(index);
			SummaryView summaryView = (SummaryView) panel.getContent();
			if(summaryView.getSummary().equals(modelo))
				insert = false;
			
			index++;
		}
		
		if(insert==true)
			this.favoritesZone.addComponent(this.getSummaryView(modelo));
	}

	@Override
	public void removeFavorite(Modelo modelo) {
		//this.favoritesPublisZone.removeComponent(this.getPubliSummaryView(publi));
	}
	
	@Override
	public void addWin(Modelo modelo){
		 boolean insert=true;
			int index=0;
			while(index<this.winZone.getComponentCount()){
				Panel panel = (Panel) this.winZone.getComponent(index);
				SummaryView summaryView = (SummaryView) panel.getContent();
				if(summaryView.getSummary().equals(modelo))
					insert = false;
				
				index++;
			}
			
			if(insert==true)
				this.winZone.addComponent(getSummaryView(modelo));
	}
	
	@Override
	public void removeWin(Modelo modelo){
			int index=0;
			while(index<this.winZone.getComponentCount()){
				Panel panel = (Panel) this.winZone.getComponent(index);
				SummaryView summaryView = (SummaryView) panel.getContent();
				if(summaryView.getSummary().equals(modelo))
					this.winZone.removeComponent(summaryView);
				
				index++;
			}
	}
}