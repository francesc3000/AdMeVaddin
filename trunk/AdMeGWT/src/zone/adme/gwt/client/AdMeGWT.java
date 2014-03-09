package zone.adme.gwt.client;

import zone.adme.gwt.client.pcontrol.PControlUI;
import zone.adme.gwt.client.sign.SignUI;
import zone.adme.gwt.client.views.ShowPubliUI;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootLayoutPanel;
/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class AdMeGWT implements EntryPoint {
  
  //Declaración de las clases globales
  ShowPubliUI sp = new ShowPubliUI();
  SignUI signUI = new SignUI(sp);
  PControlUI pcontrolUI = new PControlUI();

  @Override
  public void onModuleLoad() {
	
	RootLayoutPanel.get().add(sp);
  }
}
