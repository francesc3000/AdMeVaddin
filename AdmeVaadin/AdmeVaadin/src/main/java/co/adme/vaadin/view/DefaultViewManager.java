package co.adme.vaadin.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.util.StringUtils;

import com.vaadin.navigator.View;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;

import co.adme.vaadin.presenter.IPresenter;

@SpringComponent
@UIScope
public class DefaultViewManager implements ViewManager {
	
	@Autowired
    private ApplicationContext applicationContext;

	@Override
	public void configure(View view) {
		try{
			IPresenter p = (IPresenter) applicationContext.getBean(getPresenterName(view.getClass()));
			setupPresenter(p, view);
		}catch(Exception e){
			try{
				IPresenter p = (IPresenter) applicationContext.getBean(getPresenterNameImpl(view.getClass()));
				setupPresenter(p, view);
			}catch(Exception e2){
				//TODO: ¿Qué hacer?
			}
		}
	}
	
	@Override
	public void configure(View view, Class<?> clazz) {
		try{
			IPresenter p = (IPresenter) applicationContext.getBean(clazz);
			setupPresenter(p, view);
		}catch(Exception e){

		}
	}
	
	private void setupPresenter(IPresenter presenter, View view){
		presenter.setViewAndBind(view);
	}
	
	protected String getPresenterName(Class<?> clazz) {
		return StringUtils.uncapitalize(clazz.getSimpleName()).replace("View", "Presenter");
    }
	
	protected String getPresenterNameImpl(Class<?> clazz) {
		return StringUtils.uncapitalize(clazz.getSimpleName()).replace("ViewImpl", "Presenter");
    }
}
