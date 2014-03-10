package zone.adme.gwt.client;

import zone.adme.gwt.client.presenters.AppController;
import zone.adme.gwt.client.views.MainUI;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.ui.RootLayoutPanel;
/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class AdMeGWT implements EntryPoint {
  
  //Declaración de las clases globales
  MainUI mainUI = new MainUI();

  @Override
  public void onModuleLoad() {
	  SimpleEventBus eventBus = new SimpleEventBus();
	  /* El appController el la clase por encima de los presenters
	   * se puede utilizar para inicializar la aplicación pero sobretodo
	   * para que gestione el historico
	   */
	  AppController appController = new AppController(mainUI,eventBus);
	  
	  /*
	   * Se pone en marcha el capturador de eventos de la clase
	   */
	  appController.start();
	  /*
	   * Se pone en marcha la visualización de los widgets
	   */
	  appController.go(RootLayoutPanel.get());
  }
}
