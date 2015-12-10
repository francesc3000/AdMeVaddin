package co.adme.vaadin.view.main;

public interface MainView {	
	interface MainViewListener {
		void ItemClick(Integer position);
    }
    public void addMainListener(MainViewListener listener);
}
