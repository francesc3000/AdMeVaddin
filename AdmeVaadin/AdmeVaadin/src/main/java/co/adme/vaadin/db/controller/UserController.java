package co.adme.vaadin.db.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.SystemEnvironmentPropertySource;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.annotation.SystemProfileValueSource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.UI;

import co.adme.vaadin.db.UserRepository;
import co.adme.vaadin.modelo.User;

@RestController
@RequestMapping(value = "/rest/v1/users")
public class UserController {
	Logger logger = LoggerFactory.getLogger(getClass());
    
    @Autowired
    private MongoTemplate mongoTemplate;
     
    @Autowired
    UserRepository userRepository;
    
    public UserController(){}
    
    @RequestMapping(value = "/findOne", method = RequestMethod.GET)
    public User findOne(String id) {
    	return userRepository.findOne(id);
    }
    
    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public List<User> findAll(List<String> userIdList) {
    	List<User> userList = new ArrayList<User>();
        for(String userId:userIdList)
        	userList.add(this.findOne(userId));
        
        return userList;
    }
 
    @RequestMapping(value = "/findall", method = RequestMethod.GET)
    public List<User> getAllUser() {
    	List<User> userList = userRepository.findAll();
        return userList;
    }
   
	public @Bean User findByEmail(String email) {
		return userRepository.findByEmail(email);			
	}

	public Boolean save(User user) {
		try{
			this.userRepository.save(user);
		}catch(Exception e){
			return false;
		}
		
		return true;
	}
}
