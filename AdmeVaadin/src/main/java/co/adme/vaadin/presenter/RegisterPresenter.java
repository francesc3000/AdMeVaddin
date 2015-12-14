package co.adme.vaadin.presenter;



import com.vaadin.navigator.View;

import co.adme.vaadin.user.register.UserRegisterView;


public class RegisterPresenter  implements UserRegisterView.UserRegisterListener,
Presenter{
	
	
    private UserRegisterView view;
	
	private RegisterPresenter(){}
	

	@Override
	public void setView(View view) {
		this.view  = (UserRegisterView) view;
		
	}

	@Override
	public void bind() {
		view.addUserRegisterListener(this);
		
	}

}
