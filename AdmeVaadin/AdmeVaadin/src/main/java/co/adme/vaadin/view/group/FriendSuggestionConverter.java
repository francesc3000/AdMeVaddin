package co.adme.vaadin.view.group;

import org.vaadin.suggestfield.SuggestField.SuggestionConverter;
import org.vaadin.suggestfield.client.SuggestFieldSuggestion;

import co.adme.vaadin.modelo.User;

@SuppressWarnings("serial")
public class FriendSuggestionConverter implements SuggestionConverter{

	@Override
	public SuggestFieldSuggestion toSuggestion(Object item) {
		assert (item != null) : "Item cannot be null";
		
		String id="";
		String displayString="";
		String replacementString ="";
		
		if(item.getClass().equals(String.class))
			id = displayString = replacementString = (String) item;
		else if(item.getClass().equals(User.class)){
			User user = (User) item;
			id = user.getId();
			displayString = user.getFullName();
			replacementString = user.getEmail();
		}
		
		return new SuggestFieldSuggestion(id, displayString, replacementString);
	}

	@Override
	public Object toItem(SuggestFieldSuggestion suggestion) {
		assert (suggestion != null) : "Suggestion cannot be null";
		return suggestion.getId();
	}

}
