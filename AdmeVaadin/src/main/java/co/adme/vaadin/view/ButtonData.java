package co.adme.vaadin.view;

public class ButtonData {
	
	private String option;
	private String componentId;
	
	public String getOption() {
		return option;
	}

	public void setOption(String option) {
		this.option = option;
	}

	public String getComponentId() {
		return componentId;
	}

	public void setComponentId(String componentId) {
		this.componentId = componentId;
	}

	public ButtonData(String option){
		this.option = option;
	}
	
	public ButtonData(String option, String componentId	){
		this.option = option;
		this.componentId = componentId;
	}
}
