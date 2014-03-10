package zone.adme.gwt.client.views;

import zone.adme.gwt.shared.UsuarioGWT;
import zone.adme.gwt.client.presenters.SignPresenter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class SignUI extends Composite implements SignPresenter.Display {

	private static SignUIUiBinder uiBinder = GWT.create(SignUIUiBinder.class);
	private SignPresenter presenter = null;

	interface SignUIUiBinder extends UiBinder<Widget, SignUI> {
	}
	
	public SignUI() {
		initWidget(uiBinder.createAndBindUi(this));
		signInButton.setText("Iniciar Sesión");
		google.setText("Google+");
		this.signedPanel.setVisible(false);
	}
	
	@UiField
	HorizontalPanel signInPanel;
	@UiField
	Button signInButton;
	@UiField
	TextBox user;
	@UiField
	PasswordTextBox pass;
	@UiField
	Button google;
	
	@UiField
	HorizontalPanel signedPanel;
	@UiField
	Label nameUserLabel;
	@UiField
	Button pControlButton;
	
	@UiHandler("signInButton")
	void onClick(ClickEvent e) {
		this.presenter.login(user.getText(), pass.getText());
	}
	
	@UiHandler("google")
	void onClickGoogle(ClickEvent e){
		this.presenter.googleIn();
	}
	
	@UiHandler("pControlButton")
	void onClickPControl(ClickEvent e) {
		this.presenter.PControlClicked();
	}
	
	public UsuarioGWT getUsuarioSession(){
		return this.presenter.getUsuarioSession();
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean setPresenter(SignPresenter presenter) {
		this.presenter = presenter;
		return true;
	}

	@Override
	public void disable() {
		this.user.setEnabled(false);
		this.pass.setEnabled(false);
		this.signInButton.setEnabled(false);
		this.google.setEnabled(false);
		
	}

	@Override
	public void userRegistered(String userText) {
		this.nameUserLabel.setText(userText);
		this.signInPanel.setVisible(false);
		this.signedPanel.setVisible(true);
	}
}
