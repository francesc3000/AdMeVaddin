package zone.adme.gwt.client.views.interfaces;

import com.google.gwt.user.client.ui.IsWidget;

public interface FormPubliView extends IsWidget{
	
	void setPresenter(Presenter presenter);
	
	public interface Presenter
	{
		void crearPubli(String titulo, String descripcion);
		void startHandler();
		void stopHandler();		
	}

}
