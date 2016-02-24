package co.adme.vaadin.view.publi;

import com.vaadin.data.Container;

import co.adme.vaadin.modelo.Owner;
import co.adme.vaadin.modelo.Publi;
import co.adme.vaadin.view.ModeloView;

public interface PubliView extends ModeloView{
	interface PubliViewListener extends ModeloView.ModeloViewListener{
		void save(String publiId, String title, String description, 
				  String city, Float price, Owner ownerGroup);
		void delete(Publi publi);
		Publi getPubli(String publiId);
		Container getOwnerContainer();
		Boolean adme(Publi publi);
		Boolean unadme(Publi publi);
		Boolean isUserSponsor(Publi publi);
    }

	void addPubliListener(PubliViewListener listener);
	PubliViewListener getListener();
}
