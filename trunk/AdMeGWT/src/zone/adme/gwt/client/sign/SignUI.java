package zone.adme.gwt.client.sign;

import zone.adme.gwt.shared.UsuarioGWT;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class SignUI extends Composite implements HasText {

	private static SignUIUiBinder uiBinder = GWT.create(SignUIUiBinder.class);
	private SignIn signIn = null;

	interface SignUIUiBinder extends UiBinder<Widget, SignUI> {
	}
	
	public SignUI() {
		initWidget(uiBinder.createAndBindUi(this));
		button.setText("Iniciar Sesión");
		signIn = new SignIn();
	}

	@UiField
	Button button;
	@UiField
	TextBox textBox;

	@UiHandler("button")
	void onClick(ClickEvent e) {
		signIn.login(textBox.getText());
	}

	public void setText(String text) {
		button.setText(text);
	}

	public String getText() {
		return button.getText();
	}
	
	public UsuarioGWT getUsuarioSession(){
		return signIn.getUsuarioSession();
	}

}
