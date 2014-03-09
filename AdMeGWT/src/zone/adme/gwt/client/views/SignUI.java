package zone.adme.gwt.client.views;

import zone.adme.gwt.shared.UsuarioGWT;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class SignUI extends Composite implements HasText {

	private static SignUIUiBinder uiBinder = GWT.create(SignUIUiBinder.class);
	private SignIn signIn = null;
	private ShowPubliUI main = null;

	private RegisterUI registerUI = null;
	private PControlUI pControlUI = null;

	interface SignUIUiBinder extends UiBinder<Widget, SignUI> {
	}
	
	public SignUI(ShowPubliUI main) {
		initWidget(uiBinder.createAndBindUi(this));
		signInButton.setText("Iniciar Sesión");
		google.setText("Google+");
		signIn = new SignIn(this);
		this.main = main;
		this.main.getNorte().add(this);
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
		signIn.login(user.getText(), pass.getText());
	}
	
	@UiHandler("google")
	void onClickGoogle(ClickEvent e){
		signIn.googleIn();
	}
	
	@UiHandler("pControlButton")
	void onClickPControl(ClickEvent e) {
		this.pControlUI = new PControlUI();
		this.main.getCentro().add(pControlUI);
	}

	public void setText(String text) {
		signInButton.setText(text);
	}

	public String getText() {
		return signInButton.getText();
	}
	
	public UsuarioGWT getUsuarioSession(){
		return signIn.getUsuarioSession();
	}
	
	public boolean setUsuarioGWT(UsuarioGWT usuarioGWT){
		if(usuarioGWT!=null){
			if(usuarioGWT.getMensaje()!=null){
				Window.alert(usuarioGWT.getMensaje());
			}else{
				this.nameUserLabel.setText("Hola " + usuarioGWT.getNombre());
				this.signInPanel.setVisible(false);
				this.signedPanel.setVisible(true);
			}
		}else{
			if(this.registerUI==null){
				this.registerUI = new RegisterUI(this);
				this.registerUI.setCorreo(this.user.getText());
				this.user.setEnabled(false);
				this.pass.setEnabled(false);
				this.signInButton.setEnabled(false);
				this.google.setEnabled(false);
				this.main.getCentro().clear();
				this.main.getCentro().add(this.registerUI);
			}
			
		}
		
		return true;
	}

}
