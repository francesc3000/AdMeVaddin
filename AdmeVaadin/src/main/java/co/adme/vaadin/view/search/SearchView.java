package co.adme.vaadin.view.search;

import java.util.List;

import co.adme.vaadin.modelo.Publi;

public interface SearchView {
	public void setSearchResult(List<Publi> publiList);
	
	interface SearchViewListener {
		void searchPubliList(String searchTerm);
    }
    public void addSearchListener(SearchViewListener listener);
}
