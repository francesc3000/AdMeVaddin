package co.adme.vaadin.view.group;

import java.util.Collection;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.spring.annotation.VaadinSessionScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;

import co.adme.vaadin.modelo.Group;
import co.adme.vaadin.modelo.Modelo;
import co.adme.vaadin.presenter.GroupPresenter;
import co.adme.vaadin.view.DefaultViewManager;
import co.adme.vaadin.view.personalArea.PersonalAreaViewImpl;

@SuppressWarnings("serial")
@UIScope
@SpringView(name = GroupCRUDViewImpl.VIEW_NAME)
@VaadinSessionScope
public class GroupCRUDViewImpl extends VerticalLayout 
						   implements View, GroupView, ClickListener {
	 public static final String VIEW_NAME = "editGroup";
	 public static final String NEW_GROUP = "newGroup";
	 
	 @Autowired
	 private DefaultViewManager viewManager;

	 private Group group;
	 
	 private TextField name;
	 private MemberToken memberToken;
	 //private TokenField friendToken;

	 /* Only the presenter registers one listener... */
	private GroupViewListener listener;
	 
	 @PostConstruct
	 void init() {
		 this.viewManager.configure(this, GroupPresenter.class);
		 
		 final CssLayout wrap = new CssLayout();
		 wrap.setWidth("600px");
		 wrap.addStyleName(ValoTheme.LAYOUT_CARD);
		 addComponent(wrap);
		 setExpandRatio(wrap, 1);
		
		 name = new TextField("Nombre");
		 
		 FormLayout formLayout = new FormLayout();
		 formLayout.setMargin(true);
		 formLayout.setSpacing(true);
		 formLayout.setWidth("100%");
		 formLayout.addStyleName(ValoTheme.FORMLAYOUT_LIGHT);
		 wrap.addComponent(formLayout);	 
		 
		 formLayout.addComponent(name);

       //-----------------------------------------------------
		 
		 memberToken = new MemberToken();
         memberToken.setCaption("Miembros");
         memberToken.setWidth("100%");
         this.memberToken.setFriendList(this.getListener().getUserFriendList());
		 formLayout.addComponent(memberToken);
       //-----------------------------------------------------
		 
		 CssLayout navbar = new CssLayout();
		 addComponent(navbar);
		 
		 Button save = new Button("Guardar");
		 save.addClickListener(this);
		 save.setData("save");
		 navbar.addComponent(save);
		 
		 Button discard = new Button("Descartar Cambios");
		 discard.addClickListener(this);
		 discard.setData("discard");
		 navbar.addComponent(discard);
		 
		 Button delete = new Button("Borrar");
		 delete.addClickListener(this);
		 delete.setData("delete");
		 navbar.addComponent(delete);
    }
	 
	 private Group getGroup(String groupId){		 
		 return this.getListener().getGroup(groupId);
	 }
	 
	 private GroupViewListener getListener(){
		 return this.listener;
	 }
	 @Override
	public void addGroupListener(GroupViewListener listener) {
		this.listener = listener;
	}

	@Override
	public void enter(ViewChangeEvent event) {
		String groupId = event.getParameters();
		this.group = this.getGroup(groupId);
		
		BeanItem<Group> groupItem = new BeanItem<Group>(group);
		FieldGroup binder = new FieldGroup(groupItem);
		binder.bind(name,Group.NAME_PROPERTYID);
		
		this.memberToken.setMemberList(group.getMemberList());
		this.memberToken.setCandidateList(group.getCandidateList());
		
	}

	@Override
	public void buttonClick(ClickEvent event) {
		String name = (String) event.getButton().getData();
		switch(name){
		case "save":
			this.listener.save(this.group.getId(), this.name.getValue(), 
					   		   this.memberToken.getMemberList(), 
							   this.memberToken.getCandidateList());
			break;
		case "discard":
			
			break;
		case "delete":
			Notification.show(this.listener.delete(this.group));
			break;
		}
		
		Collection<Window> windows = UI.getCurrent().getWindows();
		for(Window window:windows)
			window.close();
		
		UI.getCurrent().getNavigator().navigateTo(PersonalAreaViewImpl.VIEW_NAME);

	}
	
	@Override
	public void setContent(Modelo group){
	}

	@Override
	public void remove(Modelo modelo) {
		//Nothing to do
	}

	@Override
	public void addFavorite(Modelo modelo) {
		//Nothing to do
	}

	@Override
	public void removeFavorite(Modelo modelo) {
		//Nothing to do
	}

	@Override
	public void addWin(Modelo modelo) {
		//Nothing to do
	}

	@Override
	public void removeWin(Modelo modelo) {
		// TODO Auto-generated method stub
		
	}
}