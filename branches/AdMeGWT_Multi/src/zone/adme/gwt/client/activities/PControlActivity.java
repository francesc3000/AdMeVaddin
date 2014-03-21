package zone.adme.gwt.client.activities;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import zone.adme.core.modelo.Publi;
import zone.adme.gwt.client.events.OpenCellListEvent;
import zone.adme.gwt.client.events.PControlClickedEvent;
import zone.adme.gwt.client.events.UserRegisteredEvent;
import zone.adme.gwt.client.mapper.PlaceControllerHolder;
import zone.adme.gwt.client.views.PubliCellViewImpl;
import zone.adme.gwt.client.views.interfaces.PControlView;
import zone.adme.gwt.shared.PubliGWT;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.web.bindery.event.shared.HandlerRegistration;
import com.google.web.bindery.event.shared.binder.EventBinder;
import com.google.web.bindery.event.shared.binder.EventHandler;

public class PControlActivity extends BaseActivity<PControlView> implements PControlView.Presenter{
	interface MyEventBinder extends EventBinder<PControlActivity> {}
	private static final MyEventBinder eventBinder = GWT.create(MyEventBinder.class);
	
	private HandlerRegistration eventRegistration = null;
	@Inject
    PlaceControllerHolder placeControllerHolder;
	
	public boolean OpenCellListClick() {
		this.placeControllerHolder.getEventBus().fireEvent(new OpenCellListEvent());

		return true;
	}
	
	@EventHandler
	void onUserRegistered(UserRegisteredEvent event){
		
	}
	
	@EventHandler
	void onOpenCellList(OpenCellListEvent event){
		PubliCellViewImpl cell = new PubliCellViewImpl();
		CellList<PubliGWT> cellList = new CellList<PubliGWT>(cell);
		List<PubliGWT> list = new ArrayList<PubliGWT>();
		
		PubliGWT publiGWT = new PubliGWT();
		publiGWT.setUsuario("Antonio Recio");
		publiGWT.setTitulo("Mariscos Recio");
		publiGWT.setTexto("Salami, Salami!");
		
		list.add(publiGWT);
		
		cellList.setRowCount(list.size(), true);  
	    cellList.setRowData(list);
	}

	@Override
	public void startHandler() {
		if(this.eventRegistration==null){
			this.eventRegistration = eventBinder.bindEventHandlers(this, this.placeControllerHolder.getEventBus());
		}
	}

	@Override
	public void stopHandler() {
		if(this.eventRegistration!=null){
			eventRegistration.removeHandler();
		}
	}

	@Override
	public void onStop() {
		this.stopHandler();
	}

	@Override
	public void onStart(AcceptsOneWidget panel) {
		panel.setWidget(getView());
        getView().setPresenter(this);
        this.startHandler();
	}	
}
