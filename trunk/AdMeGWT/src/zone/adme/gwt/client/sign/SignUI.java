package zone.adme.gwt.client.sign;

import zone.adme.gwt.client.ShowPubli;
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
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class SignUI extends Composite implements HasText {

	private static SignUIUiBinder uiBinder = GWT.create(SignUIUiBinder.class);
	private SignIn signIn = null;
	private ShowPubli main = null;

	private RegisterUI registerUI = null;

	interface SignUIUiBinder extends UiBinder<Widget, SignUI> {
	}
	
	public SignUI(ShowPubli main) {
		initWidget(uiBinder.createAndBindUi(this));
		signInButton.setText("Iniciar Sesión");
		google.setText("Google+");
		signIn = new SignIn(this);
		this.main = main;
		this.main.getNorte().add(this);
	}

	@UiField
	Button signInButton;
	@UiField
	TextBox user;
	@UiField
	PasswordTextBox pass;
	@UiField
	Button google;

	@UiHandler("signInButton")
	void onClick(ClickEvent e) {
		signIn.login(user.getText(), pass.getText());
	}
	
	@UiHandler("google")
	void onClickGoogle(ClickEvent e){
		signIn.googleIn();
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
				Window.alert("Bienvenido"+ usuarioGWT.getCorreo());
			}
		}else{
			if(this.registerUI==null){
				this.registerUI = new RegisterUI();
				this.main.getCentro().clear();
				this.main.getCentro().add(this.registerUI);
			}
			
		}
		
		return true;
	}

}
