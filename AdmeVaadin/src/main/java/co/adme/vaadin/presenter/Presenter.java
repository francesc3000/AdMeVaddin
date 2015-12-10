package co.adme.vaadin.presenter;

import com.vaadin.navigator.View;

public interface Presenter {

	void setView(View view);
    void bind();
}
