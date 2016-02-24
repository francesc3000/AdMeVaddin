package co.adme.vaadin.presenter;

import com.vaadin.navigator.View;

import co.adme.vaadin.modelo.User;

public interface IPresenter {
	User getUser();
	void setViewAndBind(View view);
}
