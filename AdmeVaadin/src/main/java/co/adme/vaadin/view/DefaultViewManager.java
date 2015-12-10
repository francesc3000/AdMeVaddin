package co.adme.vaadin.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.util.StringUtils;

import com.vaadin.navigator.View;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;

import co.adme.vaadin.presenter.Presenter;

@SpringComponent
@UIScope
public class DefaultViewManager implements ViewManager {
	
	@Autowired
    private ApplicationContext applicationContext;

	@Override
	public void configure(View view) {
		try{
			Presenter p = (Presenter) applicationContext.getBean(getPresenterName(view.getClass()));
			p.setView(view);
	        p.bind();
		}catch(Exception e){
			try{
				Presenter p = (Presenter) applicationContext.getBean(getPresenterNameImpl(view.getClass()));
				p.setView(view);
				p.bind();
			}catch(Exception e2){
				//TODO: ¿Qué hacer?
			}
		}
	}
	
	protected String getPresenterName(Class<?> clazz) {
		return StringUtils.uncapitalize(clazz.getSimpleName()).replace("View", "Presenter");
    }
	
	protected String getPresenterNameImpl(Class<?> clazz) {
		return StringUtils.uncapitalize(clazz.getSimpleName()).replace("ViewImpl", "Presenter");
    }
}
