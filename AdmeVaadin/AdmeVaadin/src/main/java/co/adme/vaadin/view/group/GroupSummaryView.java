package co.adme.vaadin.view.group;

import javax.annotation.PostConstruct;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.event.LayoutEvents.LayoutClickEvent;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;

import co.adme.vaadin.modelo.Group;
import co.adme.vaadin.modelo.Modelo;
import co.adme.vaadin.presenter.GroupPresenter;
import co.adme.vaadin.view.SummaryView;

@SuppressWarnings("serial")
public class GroupSummaryView extends SummaryView implements GroupView {
	 
	 private Group group;
	 
	 private GroupViewListener listener;
	 
	 @PostConstruct
	 protected void init() {
		 super.init();
		 viewManager.configure(this, GroupPresenter.class);		 	 
    }
	 public Group getGroup(){
		 return this.group;
	 }

	@Override
	public void enter(ViewChangeEvent event) {

	}

	@Override
	public void layoutClick(LayoutClickEvent event) {
		switch(event.getButton()){
		case LEFT:
		{
			UI.getCurrent().getSession().setAttribute(this.getGroup().getId(), this.getGroup());
			UI.getCurrent().getNavigator().navigateTo(GroupCRUDViewImpl.VIEW_NAME+"/"+this.getGroup().getId());
			break;
		}
		default:
			break;
		}
	}

	@Override
	public void buttonClick(ClickEvent event) {
		super.buttonClick(event);
	}

	@Override
	public void addGroupListener(GroupViewListener listener) {
		this.listener = listener;
	}

	@Override
	public void setContent(Modelo group) {
		if(this.group==null){
			super.setUp(group, this.listener);
			this.group = (Group) group;
			BeanItem<Group> groupItem = new BeanItem<Group>(this.getGroup());
			FieldGroup binder = new FieldGroup(groupItem);
			 
			FormLayout formLayout = new FormLayout();
			addComponent(formLayout);
			 
			TextField name = new TextField("Nombre");
			binder.bind(name,Group.NAME_PROPERTYID);
			name.setReadOnly(true);
			formLayout.addComponent(name);
		}
	}
	
	@Override
	public void remove(Modelo modelo) {
		//Nothing to do
	}
	@Override
	public void addWin(Modelo modelo) {
		//Nothing to do
	}
	@Override
	public void removeWin(Modelo modelo) {
		//Nothing to do
	}
}
