package co.adme.vaadin.view.group;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.vaadin.suggestfield.SuggestField;
import org.vaadin.suggestfield.SuggestField.NewItemsHandler;
import org.vaadin.suggestfield.SuggestField.SuggestionHandler;
import org.vaadin.suggestfield.SuggestField.TokenHandler;

import com.vaadin.data.util.ObjectProperty;
import com.vaadin.data.validator.EmailValidator;
import com.vaadin.event.LayoutEvents.LayoutClickEvent;
import com.vaadin.event.LayoutEvents.LayoutClickListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.themes.ValoTheme;

import co.adme.vaadin.modelo.User;

@SuppressWarnings("serial")
public class FriendToken_Suggest extends HorizontalLayout implements NewItemsHandler, SuggestionHandler, 
													 LayoutClickListener, TokenHandler
													 //ClickListener 
													 {
	
	private EmailValidator validator = new EmailValidator("Invalid email address");
	 
	private SuggestField suggestField = new SuggestField();
	
	List<User> friendList = new ArrayList<User>();
	
	List<String> candidateList = new ArrayList<String>();
	List<User> userList = new ArrayList<User>();
	 
	public FriendToken_Suggest(){
		super();
		suggestField.setNewItemsAllowed(true);
 		suggestField.setNewItemsHandler(this);
 		suggestField.setImmediate(true);
 		suggestField.setTokenMode(true);
 		suggestField.setSuggestionHandler(this);
 		suggestField.setSuggestionConverter(new FriendSuggestionConverter());
 		suggestField.setTokenHandler(this);
 		suggestField.setWidth("250px");
 		suggestField.setPopupWidth(400);
 		suggestField.setInputPrompt("Añade un amigo o su correo");
 		suggestField.setPropertyDataSource(new ObjectProperty<String>(User.EMAIL_PROPERTYID));
 		
 		addComponent(suggestField);
	}
	
	@Override
	public Object addNewItem(String newItemText) {
		if (validator.isValid(newItemText)) {
			/*for(User friend:friendEmailList){
				if(friend.getEmail().equals(newItemText))
					this.userList.add(friend);
				else
					this.candidateList.add(newItemText);
			}*/
		}
		return newItemText;
	}
	
	@Override
	public void handleToken(Object token) {
		if (token != null) {
			
			if(token.getClass().equals(String.class)){
				final String address = (String) token;
				// Skip duplicates
				if (!getValue().contains(address)) 
					addToken(generateToken(address));
			}
			else if(token.getClass().equals(User.class)){
				final User friend = (User) token;
				// Skip duplicates 
				if (!getValue().contains(friend.getFullName())
				 && !getValue().contains(friend.getEmail()))
					addToken(generateToken(friend));
			}
		}
	}
	
	private List<String> getValue() {
		List<String> values = new LinkedList<String>();
		for (int i = 0; i < getComponentCount(); i++) {
			if (getComponent(i) instanceof Button) {
				final Button btn = (Button) getComponent(i);
				values.add((String) btn.getData());
			}
		}
		return values;
	}
	
	private Button generateToken(Object token) {
		String address = null;
		if(token.getClass().equals(String.class))
			address = (String) token;
		else if(token.getClass().equals(User.class)){
			final User friend = (User) token;
			address = friend.getFullName();
		}
		final Button btn = new Button(address, FontAwesome.TIMES);
		btn.setData(address);
		btn.addStyleName(ValoTheme.BUTTON_SMALL);
		btn.addStyleName(ValoTheme.BUTTON_ICON_ALIGN_RIGHT);
		btn.addClickListener(event -> addressRemoveClick(event));

		if (validator.isValid(address)) {
			btn.setDescription("Click to remove");
		} else {
			btn.addStyleName(ValoTheme.BUTTON_DANGER);
			btn.setDescription(validator.getErrorMessage());
		}
		return btn;
	}
	
	private void addressRemoveClick(ClickEvent event){
		this.removeComponent(event.getButton());
		//event.getButton().removeClickListener(this);
	}
	
	private void addToken(Button button) {
		int index = getComponentIndex(suggestField);
		addComponent(button, index);
	}

	@Override
	public void layoutClick(LayoutClickEvent event) {
		if (event.getClickedComponent() == null) {
			suggestField.focus();
		}
	}

	@Override
	public List<Object> searchItems(String query) {
		if ("".equals(query) || query == null) {
			return Collections.emptyList();
		}
		List<User> result = new ArrayList<User>();
		
		int count = 0;
		for (User friend : this.friendList) {
			if (friend.getEmail().toLowerCase().startsWith(query.toLowerCase())
			 ||(friend.getFullName().toLowerCase().contains(query.toLowerCase()))
			    && count < 10) 
			{
				result.add(friend);
				count++;
			}
		}
		System.out.println("Total: " + result.size());

		return new ArrayList<Object>(result);
	}
	/*
	@Override
	public void buttonClick(ClickEvent event) {
		// TODO Auto-generated method stub
		
	}
	*/
	
	public void setFriendList(List<User> friendList){
		this.friendList = friendList;
	}
	
	public List<User> getFriendList(){
		return this.friendList;
	}
	
	public List<String> getCandidatesList(){
		return this.candidateList;
	}
}
