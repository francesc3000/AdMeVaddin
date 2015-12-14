package co.adme.vaadin.user.register;

import java.util.List;

import co.adme.vaadin.modelo.Publi;

public interface  UserRegisterView {
	
	//public void setSearchResult(List<Publi> publiList);
	
	interface UserRegisterListener {
		
    }
    public void addUserRegisterListener(UserRegisterListener listener);
}

