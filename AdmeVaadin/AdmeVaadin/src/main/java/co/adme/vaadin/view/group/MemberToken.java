package co.adme.vaadin.view.group;

import java.util.List;

import org.vaadin.tokenfield.TokenField;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.validator.EmailValidator;
import com.vaadin.shared.ui.combobox.FilteringMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.themes.Reindeer;

import co.adme.vaadin.modelo.User;

@SuppressWarnings("serial")
public class MemberToken extends HorizontalLayout {
	
	private EmailValidator validator = new EmailValidator("Invalid email address");
	 
	private TokenField tokenField;
	private BeanItemContainer<User> tokensContainer = new BeanItemContainer<User>(User.class);
	 
	private List<User> memberList;
	private List<String> candidateList;
	
	public MemberToken(){
		super();
		tokenField = new TokenField() {
            protected void onTokenInput(Object tokenId) {
            	if (!cb.containsId(tokenId)){
            		String tokenEmail = getTokenEmail(tokenId);
                    if(!validator.isValid(tokenEmail)){
                    	validator.setErrorMessage("Correo no valido");
                    }else
                    	addToken(tokenId);
                    
            	}
            	else
            		addToken(tokenId);
            	  
            }
            
            public void addToken(Object tokenId){
            	super.addToken(tokenId);
            	
            	if(tokenId.getClass().equals(User.class)){
            		User member = (User) tokenId;
            		if(!memberList.contains(member))
            			memberList.add(member);
            	}
            	else{
            		String candidate = (String) tokenId;
            		if(!candidateList.contains(candidate))
            			candidateList.add(candidate);
            	}
            	
            }

            protected void onTokenClick(final Object tokenId) {
            	Boolean l_remove = true;
            	//Si se trata de la primera posición no se borra
            	if(tokenId.getClass().equals(User.class))
            		if(memberList.get(0).equals(tokenId))
            			l_remove = false;
            	
            	if(l_remove==true){
            		super.onTokenClick(tokenId);
	            	if(tokenId.getClass().equals(User.class))
	            		memberList.remove((User)tokenId);
	            	else
	            		candidateList.remove((String)tokenId);
            	}
            }

            /*           
            // show confirm dialog
            protected void onTokenClick(final Object tokenId) {

            }
*/
            // just delete, no confirm
            protected void onTokenDelete(Object tokenId) {

            }
            protected void configureTokenButton(Object tokenId,
                    Button button) {
            	//La primera posición se muestra diferente
            	if(tokenId.getClass().equals(User.class)){
            		if(memberList.get(0).equals(tokenId)){
            			button.setCaption(getTokenCaption(tokenId));
            	        button.setIcon(getTokenIcon(tokenId));
            	        //button.setDescription("Click to remove");
            	        button.setStyleName(Reindeer.BUTTON_DEFAULT);
            		}else{
            			// default sets  sets style & caption
    	                super.configureTokenButton(tokenId, button);
    	                button.setDescription("Click para borrar");
            		}
            	}else{
	                // default sets  sets style & caption
	                super.configureTokenButton(tokenId, button);
	                button.setDescription("Click para borrar");
            	}

                // width
                button.setWidth("100%");

                if(!cb.containsId(tokenId)){
                	if(!tokenId.getClass().equals(User.class))
                		button.addStyleName(TokenField.STYLE_BUTTON_EMPHAZISED);
                }
            }
        };
        tokenField.setStyleName(TokenField.STYLE_TOKENFIELD); // remove fake textfield look
        tokenField.setWidth("100%"); // width...
        tokenField.setInputWidth("100%"); // and input width separately
        tokenField.setFilteringMode(FilteringMode.CONTAINS); // suggest
        tokenField.setInputPrompt("Introduce un miembro o su correo");
        tokenField.setRememberNewTokens(false); // we can opt to do this ourselves
        tokenField.setNewTokensAllowed(true);
        addComponent(tokenField);
        
        //tokensContainer.addContainerProperty(User.EMAIL_PROPERTYID, User.class, null);
	}
	
	private String getTokenEmail(Object tokenId){
		String tokenEmail;
		if(tokenId.getClass().equals(User.class)){
			User member = (User) tokenId;
			tokenEmail = member.getEmail();
		}else
			tokenEmail = (String) tokenId;
		
		return tokenEmail;
	}
	
	public void setFriendList(List<User> friendList){
		this.setContinerDataSource(friendList);
	}
	
	private void setContinerDataSource(List<User> friendList){		
		for(User friend:friendList)
			tokensContainer.addBean(friend);
		
		tokenField.setContainerDataSource(tokensContainer);
	}
	
	public void setMemberList(List<User> memberList){
		this.memberList = memberList;
		for(User miembro:this.memberList)
			this.tokenField.addToken(miembro);
	}
	
	public void setCandidateList(List<String> candidateList){
		this.candidateList = candidateList;
		for(String candidate:this.candidateList)
			this.tokenField.addToken(candidate);
	}
	
	public List<User> getMemberList(){
		/*
		List<User> memberList = new ArrayList<User>();
		for(Object tokenid:this.tokenField.getTokenIds())
			if(tokenid.getClass().equals(User.class))
				memberList.add((User) tokenid);
		
		return memberList;
		*/
		return this.memberList;
	}
	
	public List<String> getCandidateList(){
		/*
		List<String> candidateList = new ArrayList<String>();
		for(Object tokenid:this.tokenField.getTokenIds())
			if(tokenid.getClass().equals(String.class))
				candidateList.add((String) tokenid);
		
		return candidateList;
		*/
		return this.candidateList;
	}
}
