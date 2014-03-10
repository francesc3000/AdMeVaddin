package zone.adme.gwt.client.presenters;

import java.util.ArrayList;
import java.util.List;

import zone.adme.gwt.client.events.OpenCellListEvent;
import zone.adme.gwt.client.events.PControlClickedEvent;
import zone.adme.gwt.client.events.UserNotRegisteredEvent;
import zone.adme.gwt.client.views.MainUI;
import zone.adme.gwt.client.views.PControlUI;
import zone.adme.gwt.client.views.PubliCellUI;
import zone.adme.gwt.client.views.RegisterUI;
import zone.adme.gwt.client.views.SignUI;
import zone.adme.gwt.shared.PubliGWT;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.client.ui.Panel;
import com.google.web.bindery.event.shared.HandlerRegistration;
import com.google.web.bindery.event.shared.binder.EventBinder;
import com.google.web.bindery.event.shared.binder.EventHandler;

public class AppController implements Presenter {
	interface MyEventBinder extends EventBinder<AppController> {}
	private static final MyEventBinder eventBinder = GWT.create(MyEventBinder.class);
		
	private MainUI  main = null;
	private EventBus eventBus = null;
	private HandlerRegistration eventRegistration = null;
	
	public AppController(MainUI main, EventBus eventBus){
		this.main = main;
		this.eventBus = eventBus;
	}

	@Override
	public boolean bind() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean start() {
		if(this.eventRegistration==null){
			this.eventRegistration = eventBinder.bindEventHandlers(this, this.eventBus);
		}
		return true;
	}

	@Override
	public boolean stop() {
		if(this.eventRegistration!=null){
			eventRegistration.removeHandler();
		}
		return true;
	}
	
	public boolean go(Panel panel){
		SignUI signUI = new SignUI();
		SignPresenter signPresenter = new SignPresenter(signUI,eventBus);
		signPresenter.start();
		main.getNorte().add(signUI);
		panel.add(main);
		
		return true;
	}
	
	@EventHandler
	 void onUserNotRegistered(UserNotRegisteredEvent event) {
	    RegisterUI registerUI = new RegisterUI();
	    RegisterPresenter registerPresenter = new RegisterPresenter(registerUI, eventBus);
	    registerPresenter.start();
	    main.getCentro().add(registerUI);
	 }
	
	@EventHandler
	 void onPControlClicked(PControlClickedEvent event) {
	    PControlUI pControlUI = new PControlUI();
	    PControlPresenter pControlPresenter = new PControlPresenter(pControlUI, eventBus);
	    pControlPresenter.start();
	    main.getCentro().add(pControlUI);
	 }
	
	@EventHandler
	void onOpenCellList(OpenCellListEvent event){
		
		PubliCellUI publiCellUI = new PubliCellUI();
		CellList<PubliGWT> cellList = new CellList<PubliGWT>(publiCellUI);
		List<PubliGWT> lista = new ArrayList<PubliGWT>();
		
		PubliGWT p1 = new PubliGWT();
		
		p1.setUsuario("Alberto");
		p1.setTitulo("TITULO 1");
		p1.setTexto("Esta es la publicacion numero 1");
		
		PubliGWT p2 = new PubliGWT();
		
		p2.setUsuario("Alfonso Javier");
		p2.setTitulo("TITULO 2");
		p2.setTexto("Esta es la publicacion numero 2, iauygp aui apuihgpa aui gauerp ahit ");
		
		lista.add(p1);
		lista.add(p2);
		
		cellList.setRowCount(lista.size(),true);
		cellList.setRowData(lista);
		 main.getCentro().add(cellList);
	}

}
