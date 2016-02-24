package co.adme.vaadin.presenter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.navigator.View;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;

import co.adme.vaadin.db.PubliRepository;
import co.adme.vaadin.db.controller.PubliController;
import co.adme.vaadin.modelo.Constants;
import co.adme.vaadin.modelo.Publi;
import co.adme.vaadin.view.publi.PubliView;
import co.adme.vaadin.view.search.SearchView;

@SpringComponent
@UIScope
public class SearchPresenter extends Presenter implements SearchView.SearchViewListener {
	
	@Autowired
	private PubliController service;
	
    private List<SearchView> viewList = new ArrayList<SearchView>();
	
	private SearchPresenter(){}
	
	@Override
	public void searchPubliList(String searchTerm, String searchCity) {
		List<Publi> publiList = null;
		
		if(searchCity.compareTo("Todas")==0){searchCity = "";}
		publiList = service.findByLookupContainingAndCityContaining(searchTerm.toUpperCase(), searchCity);
		
		for(SearchView searchView:this.viewList)
			searchView.setSearchResult(publiList);
	}
	
	@Override
	public List<Publi> searchPubliListPage(String searchTerm, String searchCity, Boolean newSearch) {
		List<Publi> publiList = new ArrayList<Publi>();
		if(searchCity.compareTo("Todas")==0){searchCity = "";}
		publiList.addAll(service.findByLookupContainingAndCityContainingPageable(searchTerm.toUpperCase(), searchCity, newSearch));
		
		return publiList;
	}

	@Override
	public void setViewAndBind(View view) {
		super.setViewAndBind(view);
		SearchView searchView = (SearchView) view;
		this.viewList.add(searchView);
		searchView.addSearchListener(this);
	}

	@Override
	public List<String> getAvailableCities() {
		List<String> availablesCities = new ArrayList<String>();
		
		availablesCities.add("Todas");
		availablesCities.addAll(Constants.CITIES);
		
		return availablesCities;
	}	
}
