package zone.adme.gwt.client.sign;

import zone.adme.gwt.client.services.UserService;
import zone.adme.gwt.client.services.UserServiceAsync;
import zone.adme.gwt.shared.UsuarioGWT;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class RegisterUI extends Composite implements HasText {
	
	private final UserServiceAsync signService = GWT.create(UserService.class);
	private SignUI padre = null;
	
	private static RegisterUiBinder uiBinder = GWT
			.create(RegisterUiBinder.class);

	interface RegisterUiBinder extends UiBinder<Widget, RegisterUI> {
	}

	public RegisterUI() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	public RegisterUI(SignUI padre) {
		initWidget(uiBinder.createAndBindUi(this));
		this.padre = padre;
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
	Button register;

	public RegisterUI(String firstName) {
		initWidget(uiBinder.createAndBindUi(this));
		register.setText(firstName);
	}

	@UiHandler("register")
	void onClick(ClickEvent e) {
		signService.register(this.correo.getText(), this.contrasena.getText(), 
						     this.nombre.getText(), this.apellido1.getText(),
						     this.apellido2.getText(), new AsyncCallback<UsuarioGWT>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Fallo conexion");
				
			}

			@Override
			public void onSuccess(UsuarioGWT usuarioGWT) {
				setUsuarioGWT(usuarioGWT);
			}
			
			
		});
	}
	
	private boolean setUsuarioGWT(UsuarioGWT usuarioGWT){
		return this.padre.setUsuarioGWT(usuarioGWT);
	}

	public void setText(String text) {
		register.setText(text);
	}

	public String getText() {
		return register.getText();
	}
	
	public boolean setCorreo(String correo){
		this.correo.setText(correo);
		
		return true;
	}

}
