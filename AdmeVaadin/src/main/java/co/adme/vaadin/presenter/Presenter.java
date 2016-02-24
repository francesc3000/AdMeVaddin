package co.adme.vaadin.presenter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.navigator.View;
import com.vaadin.ui.UI;

import co.adme.vaadin.db.controller.GroupController;
import co.adme.vaadin.db.controller.PubliController;
import co.adme.vaadin.db.controller.UserController;
import co.adme.vaadin.modelo.User;
import co.adme.vaadin.view.publi.PubliView;

public class Presenter implements IPresenter {
	
	@Autowired
	protected UserController userService;
	
	@Autowired
	protected GroupController groupService;
	
	@Autowired
	protected PubliController publiService;

	private List<View> viewList = new ArrayList<View>();

	@Override
	public User getUser() {
		//TODO: Quitar la siguente linea de código y dejar que se recupere unicamente
		// de la sessión
		User user = UI.getCurrent().getSession().getAttribute(User.class);
		if(user==null){
			user = userService.findByEmail("francesc3000@gmail.com");
			if(user==null){
				user = new User("francesc3000@gmail.com", "Francesc", "Muñoz", "Romero");
				User friend = new User("friend@friend.com", "Amigo", "Primer Apellido", "Segundo Apellido");
				userService.save(friend);
				user.addFriend(friend);
				userService.save(user);
			}
			UI.getCurrent().getSession().setAttribute(User.class, user);
		}
		return user;
	}
	
	protected Boolean saveUser(){
		return this.getUser().save();
	}

	@Override
	public void setViewAndBind(View view) {
		this.viewList.add(view);
	}
}
