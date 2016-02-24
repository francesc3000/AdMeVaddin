package co.adme.vaadin.view.publi;

import java.util.Collection;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.turim.ui.CurrencyField;

import com.vaadin.data.Container;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.shared.ui.combobox.FilteringMode;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.spring.annotation.VaadinSessionScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

import co.adme.vaadin.modelo.Constants;
import co.adme.vaadin.modelo.Modelo;
import co.adme.vaadin.modelo.Owner;
import co.adme.vaadin.modelo.Publi;
import co.adme.vaadin.presenter.PubliPresenter;
import co.adme.vaadin.view.DefaultViewManager;
import co.adme.vaadin.view.personalArea.PersonalAreaViewImpl;

@SuppressWarnings("serial")
@UIScope
@SpringView(name = PubliCRUDViewImpl.VIEW_NAME)
@VaadinSessionScope
public class PubliCRUDViewImpl extends VerticalLayout 
						   implements View, PubliView, ClickListener {
	 public static final String VIEW_NAME = "publi";
	 public static final String NEW_PUBLI = "newPubli";
	 
	 @Autowired
	 private DefaultViewManager viewManager;

	 private Publi publi;
	 
	 private TextField title;
	 private TextField description;
	 private ComboBox city;
	 private CurrencyField price;
	 private ComboBox owner;	 
	 
	 /* Only the presenter registers one listener... */
	private PubliViewListener listener;
	 
	 @PostConstruct
	 void init() {
		 this.viewManager.configure(this, PubliPresenter.class);
		 
		 FormLayout formLayout = new FormLayout();
		 addComponent(formLayout);

		 title = new TextField("Título");
		 formLayout.addComponent(title);
		 
		 description = new TextField("Descripción");
		 formLayout.addComponent(description);
		 
		 city = new ComboBox("Ciudad");
		 city.setNullSelectionAllowed(false);
		 BeanItemContainer<String> cityContainer = new BeanItemContainer<String>(String.class);
		 cityContainer.addAll(Constants.CITIES);
		 city.setContainerDataSource(cityContainer);
		 formLayout.addComponent(city);
		 
		 price = new CurrencyField();
		 price.setCaption("Precio");
		 //price.setSigned(false);
		 //price.setDecimal(true);
		 //price.setDecimalLength(2);
		 //price.setDecimalSeparator('.');
		 formLayout.addComponent(price);
		 
		 owner = new ComboBox("Propietario");
		 owner.setFilteringMode(FilteringMode.CONTAINS);
		 owner.setContainerDataSource(this.getOwnerContainer());
		 owner.setNullSelectionAllowed(false);
		 formLayout.addComponent(owner);
		 
		 CssLayout navbar = new CssLayout();
		 addComponent(navbar);
		 
		 Button save = new Button("Guardar");
		 save.addClickListener(this);
		 save.setData("save");
		 navbar.addComponent(save);
		 
		 Button discard = new Button("Descartar");
		 discard.addClickListener(this);
		 discard.setData("discard");
		 navbar.addComponent(discard);
		 
		 Button delete = new Button("Borrar");
		 delete.addClickListener(this);
		 delete.setData("delete");
		 navbar.addComponent(delete);
    }
	 
	 private Container getOwnerContainer(){
		 return this.getListener().getOwnerContainer();
	 }
	 
	 private Publi getPubli(String publiId){
		 return this.getListener().getPubli(publiId);
	 }
	 
	@Override
	public PubliViewListener getListener(){
		return this.listener;
	}
	 
	@Override
	public void addPubliListener(PubliViewListener listener) {
		this.listener = listener;
	}

	@Override
	public void enter(ViewChangeEvent event) {
		String publiId = event.getParameters();
		publi = this.getPubli(publiId);

		this.setupView(publi);
	}
	
	public void setupView(Publi publi){
		BeanItem<Publi> publiItem = new BeanItem<Publi>(publi);
		FieldGroup binder = new FieldGroup(publiItem);
		binder.bind(title,Publi.TITLE_PROPERTYID);
		binder.bind(description,Publi.DESCRIPTION_PROPERTYID);
		binder.bind(city,Publi.CITY_PROPERTYID);
		binder.bind(price,Publi.PRICE_PROPERTYID);
		binder.bind(owner, Publi.OWNER);
		
		//owner.setValue(publi.getOwner().getName());
		//owner.setContainerDataSource(this.getOwnerContainer());
	}

	@Override
	public void buttonClick(ClickEvent event) {
		String name = (String) event.getButton().getData();
		
		switch(name){
		case "save":
			Owner owner = (Owner) this.owner.getValue();
			String price = this.price.getValue();
			price = price.replace(",", ".");
			this.listener.save(this.publi.getId(), this.title.getValue(),this.description.getValue(), 
							   this.city.getValue().toString(), new Float(price), owner);
			break;
		case "discard":
			
			break;
		case "delete":
			this.listener.delete(this.publi);
			break;
		}
		Collection<Window> windows = UI.getCurrent().getWindows();
		for(Window window:windows)
			window.close();
		
		UI.getCurrent().getNavigator().navigateTo(PersonalAreaViewImpl.VIEW_NAME);
	}

	@Override
	public void setContent(Modelo publi) {
		//Nothing to do
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
		//Nothing to do
	}
}