package zone.adme.gwt.client.views;

import javax.inject.Inject;

import zone.adme.gwt.client.activities.RegisterActivity;
import zone.adme.gwt.client.views.interfaces.RegisterView;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class RegisterViewImpl extends Composite implements RegisterView {
	
	private static RegisterUiBinder uiBinder = GWT
			.create(RegisterUiBinder.class);
	
	private RegisterActivity presenter = null;

	@UiTemplate("RegisterView.ui.xml")
	interface RegisterUiBinder extends UiBinder<Widget, RegisterViewImpl> {
	}

	@Inject
	public RegisterViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	@UiField
	TextBox correo;
	@UiField
	PasswordTextBox contrasena;
	@UiField
	TextBox nombre;
	@UiField
	TextBox apellido1;
	@UiField
	TextBox apellido2;
	@UiField
	Button registerButton;

	public RegisterViewImpl(String firstName) {
		initWidget(uiBinder.createAndBindUi(this));
		registerButton.setText(firstName);
	}

	@UiHandler("registerButton")
	void onClickRegister(ClickEvent e) {
		this.presenter.registrar(this.correo.getText(), this.contrasena.getText(), 
			     this.nombre.getText(), this.apellido1.getText(),
			     this.apellido2.getText());
		
	}
	
	public boolean setCorreo(String correo){
		this.correo.setText(correo);
		return true;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setPresenter(RegisterActivity presenter) {
		this.presenter = presenter;
	}

}
