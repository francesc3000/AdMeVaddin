package co.adme.vaadin.presenter;



import java.util.ArrayList;
import java.util.List;

import com.vaadin.navigator.View;

import co.adme.vaadin.user.register.UserRegisterView;
import co.adme.vaadin.view.publi.PubliView;


public class RegisterPresenter extends Presenter implements UserRegisterView.UserRegisterListener {
	
	private List<UserRegisterView> viewList = new ArrayList<UserRegisterView>();
	
	private RegisterPresenter(){}

	@Override
	public void setViewAndBind(View view) {
		super.setViewAndBind(view);
		UserRegisterView userRegisterView = (UserRegisterView) view;
		this.viewList.add(userRegisterView);
		userRegisterView.addUserRegisterListener(this);
	}

}
