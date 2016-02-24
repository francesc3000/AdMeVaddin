package co.adme.vaadin.view.group;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.spring.annotation.VaadinSessionScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.UI;

import co.adme.vaadin.modelo.Modelo;
import co.adme.vaadin.view.DefaultViewManager;
import co.adme.vaadin.view.personalArea.PersonalAreaViewLayout;

@SuppressWarnings("serial")
@UIScope
@SpringView(name = GroupViewImpl.VIEW_NAME)
@VaadinSessionScope
public class GroupViewImpl extends PersonalAreaViewLayout 
						   implements GroupView, ClickListener{
	 public static final String VIEW_NAME = "groupList";
	 
	 @Autowired
	 private DefaultViewManager viewManager;
	 
	 /* Only the presenter registers one listener... */
	 private GroupViewListener listener;
	 
	 @PostConstruct
	 protected void init() {
		 super.init();
		 super.setSummaryViewClass(GroupSummaryView.class);
		 this.viewManager.configure(this);
		 
		 Button newGroup = new Button("Nuevo Grupo");
		 newGroup.setData(GroupCRUDViewImpl.NEW_GROUP);
		 newGroup.addClickListener(this);
		 addComponent(newGroup);
		 
		 this.setOwnerPanelCaption("Mis Grupos");
	     this.setFavoritePanelCaption("Mis Favoritos");
	     //this.setWinPanelCaption("Mis Publicaciones Ganadas");
    }
	 
	@Override
	public void addGroupListener(GroupViewListener listener) {
		this.listener = listener;
	}

	@Override
	public void enter(ViewChangeEvent event) {
		List<Modelo> ownerList = new ArrayList<Modelo>();
		ownerList.addAll(listener.getGroupList());
		this.setOwnerList(ownerList);
		this.setFavoriteList(listener.getFavoriteList());
		
		super.enter(event);
	}
	
	public void load(){	     
		List<Modelo> ownerList = new ArrayList<Modelo>();
		ownerList.addAll(listener.getGroupList());
		this.setOwnerList(ownerList);
		this.setFavoriteList(listener.getFavoriteList());
		
		super.load();
	}

	@Override
	public void buttonClick(ClickEvent event) {
		String option = (String) event.getButton().getData();
		switch(option){
		case GroupCRUDViewImpl.NEW_GROUP:
			/*
			window.setCaption("Nuevo grupo");
			window.setModal(true);
			window.setClosable(false);
			groupCRUDViewImpl.setGroup(new Group());
			window.setContent(groupCRUDViewImpl);
			UI.getCurrent().addWindow(window);
			*/
			UI.getCurrent().getNavigator().navigateTo(GroupCRUDViewImpl.VIEW_NAME+"/"+GroupCRUDViewImpl.NEW_GROUP);
			
			break;
		}
	}
}