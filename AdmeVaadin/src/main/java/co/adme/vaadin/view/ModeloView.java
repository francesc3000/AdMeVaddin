package co.adme.vaadin.view;

import java.util.List;

import co.adme.vaadin.modelo.Modelo;

public abstract interface ModeloView{
	abstract interface ModeloViewListener{
		List<Modelo> getMyPubliList();
		List<Modelo> getFavoriteList();
		List<Modelo> getWinPubliList();
		void addFavorite(Modelo modelo);
		void removeFavorite(Modelo modelo);
    }
	
	void setContent(Modelo modelo);
	void remove(Modelo modelo);
    void addFavorite(Modelo modelo);
    void removeFavorite(Modelo modelo);
    void addWin(Modelo modelo);
    void removeWin(Modelo modelo);
}
