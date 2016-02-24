package co.adme.vaadin.presenter;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.navigator.View;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;

import co.adme.vaadin.view.personalArea.PersonalAreaView;
import co.adme.vaadin.view.publi.PubliView;

@SpringComponent
@UIScope
public class PersonalAreaPresenter extends Presenter implements PersonalAreaView.PersonalAreaViewListener{
	
	private List<PersonalAreaView> viewList = new ArrayList<PersonalAreaView>();
	
	private PersonalAreaPresenter(){}

	@Override
	public void setViewAndBind(View view) {
		super.setViewAndBind(view);
		PersonalAreaView personalAreaView = (PersonalAreaView) view;
		this.viewList.add(personalAreaView);
		personalAreaView.addPersonalAreaListener(this);
	}
}
