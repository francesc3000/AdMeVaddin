package co.adme.vaadin.db.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.UI;

import co.adme.vaadin.db.GroupRepository;
import co.adme.vaadin.db.UserRepository;
import co.adme.vaadin.modelo.Group;
import co.adme.vaadin.modelo.User;

@RestController
@RequestMapping(value = "/rest/v1/groups")
public class GroupController {
	Logger logger = LoggerFactory.getLogger(getClass());
    
    @Autowired
    private MongoTemplate mongoTemplate;
     
    @Autowired
    private GroupRepository groupRepository;
    
    public GroupController(){}
    
    @RequestMapping(value = "/findOne", method = RequestMethod.GET)
    public Group findOne(String id) {
    	Group group = groupRepository.findOne(id);
        return group;
    }
    
    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public List<Group> findAll(List<String> groupIdList) {
    	List<Group> groupList = new ArrayList<Group>();
        for(String groupId:groupIdList)
        	groupList.add(this.findOne(groupId));
        
        return groupList;
    }
 
    @RequestMapping(value = "/findallgroup", method = RequestMethod.GET)
    public List<Group> getAllGroup() {
    	List<Group> groupList = groupRepository.findAll();
        return groupList;
    }

	public Boolean save(Group group) {
		try{
			this.groupRepository.save(group);
		}catch(Exception e){
			return false;
		}
		
		return true;
	}

	public void delete(Group group) {
		this.groupRepository.delete(group);
	}
	
	public boolean delete(String groupId) {
		this.groupRepository.delete(groupId);
		
		return true;
	}
}
