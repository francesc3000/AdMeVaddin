package co.adme.vaadin.view.search;

import java.util.List;

import co.adme.vaadin.modelo.Publi;

public interface SearchView {
	interface SearchViewListener {
		List<String> getAvailableCities();
		void searchPubliList(String searchTerm, String searchCity);
		List<Publi> searchPubliListPage(String searchTerm, String searchCity, Boolean newSearch);
    }

    public void addSearchListener(SearchViewListener listener);
    public void setSearchResult(List<Publi> publiList);
}
