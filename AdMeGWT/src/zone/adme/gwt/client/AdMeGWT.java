package zone.adme.gwt.client;

import zone.adme.gwt.client.presenters.AppController;
import zone.adme.gwt.client.views.MainUI;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.shared.SimpleEventBus;
/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class AdMeGWT implements EntryPoint {
  
  //Declaración de las clases globales
  MainUI mainUI = new MainUI();

  @Override
  public void onModuleLoad() {
	  SimpleEventBus eventBus = new SimpleEventBus();
	
	  AppController appController = new AppController(mainUI,eventBus);
	  appController.start();
	  appController.go();
  }
}
