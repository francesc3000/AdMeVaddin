package co.adme.vaadin.presenter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.navigator.View;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;

import co.adme.vaadin.db.PubliRepository;
import co.adme.vaadin.modelo.Group;
import co.adme.vaadin.modelo.Publi;
import co.adme.vaadin.modelo.User;
import co.adme.vaadin.view.search.SearchView;

@SpringComponent
@UIScope
public class SearchPresenter implements SearchView.SearchViewListener,
										Presenter{
	
	@Autowired
	private PubliRepository service;
	
    private SearchView view;
	
	private SearchPresenter(){}
	
	@Override
	public void searchPubliList(String searchTerm) {
		List<Publi> publiList = null;
		
		//service.save(new Publi("Pubi 1", "Descripción de la publi 1", "Barcelona", new Float(1000)));
		//service.save(new Publi("Pubi 2", "Descripción de la publi 2", "Barcelona", new Float(2000)));
		//service.save(new Publi("Pubi 3", "Descripción de la publi 3", "Cuenca", new Float(3000)));
		
		
		/*
		service.save(new Publi(new Group("Grupo 1", new User("francesc3000@gmail.com", "Francesc", "Muñoz", "Romero")),"Pubi 1", "Descripción de la publi 1", "Barcelona", new Float(1000)));
		service.save(new Publi(new Group("Grupo 2", new User("francesc3000@gmail.com", "Francesc", "Muñoz", "Romero")),"Pubi 2", "Descripción de la publi 2", "Barcelona", new Float(2000)));
		service.save(new Publi(new Group("Grupo 3", new User("francesc3000@gmail.com", "Francesc", "Muñoz", "Romero")),"Pubi 3", "Descripción de la publi 3", "Cuenca", new Float(3000)));
		
		if(searchTerm!=null){
			publiList = service.findByLookupContaining(searchTerm.toUpperCase());
		}
		*/
		publiList = service.findAll();
		this.view.setSearchResult(publiList);
	}

	@Override
	public void setView(View view) {
		this.view  = (SearchView) view;
	}

	@Override
	public void bind() {           
		view.addSearchListener(this);
	}
}
