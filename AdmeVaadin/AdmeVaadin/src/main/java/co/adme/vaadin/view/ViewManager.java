package co.adme.vaadin.view;

import com.vaadin.navigator.View;

public interface ViewManager {
	void configure(View view);
	void configure(View view, Class<?> clazz);
}
