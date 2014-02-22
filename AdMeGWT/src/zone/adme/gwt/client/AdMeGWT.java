package zone.adme.gwt.client;

import zone.adme.gwt.client.pcontrol.PControlUI;
import zone.adme.gwt.client.sign.SignUI;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.RootPanel;
/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class AdMeGWT implements EntryPoint {
  
  //Declaración de las clases globales
  ShowPubli sp = new ShowPubli();
  SignUI signUI = new SignUI(sp);
  PControlUI pcontrolUI = new PControlUI();

  @Override
  public void onModuleLoad() {
	
	//RootLayoutPanel.get().add(sp);
	RootPanel.get().add(pcontrolUI);
  }
}
