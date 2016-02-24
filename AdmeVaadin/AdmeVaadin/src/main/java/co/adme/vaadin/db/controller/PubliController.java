package co.adme.vaadin.db.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vaadin.ui.UI;

import co.adme.vaadin.db.PubliRepository;
import co.adme.vaadin.modelo.Publi;

@RestController
@RequestMapping(value = "/rest/v1/publis")
public class PubliController {
	Logger logger = LoggerFactory.getLogger(getClass());
    
    @Autowired
    private MongoTemplate mongoTemplate;
     
    @Autowired
    private PubliRepository publiRepository;
    
    private Pageable pageable;
    
    public PubliController(){}
    
    @RequestMapping(value = "/findOne", method = RequestMethod.GET)
    public Publi findOne(String id) {
    	Publi publi = publiRepository.findOne(id);
        return publi;
    }
    
    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public List<Publi> findAll(List<String> publiIdList) {
    	List<Publi> publiList = new ArrayList<Publi>();
        for(String publiId:publiIdList)
        	publiList.add(this.findOne(publiId));
        
        return publiList;
    }
 
    @RequestMapping(value = "/findall", method = RequestMethod.GET)
    public List<Publi> getAllGroup() {
    	List<Publi> publiList = publiRepository.findAll();
        return publiList;
    }

	public Boolean save(Publi publi) {
		try{
			this.publiRepository.save(publi);
		}catch(Exception e){
			return false;
		}
	
		return true;
	}

	public void delete(Publi publi) {
		this.publiRepository.delete(publi);
	}
	
	public void delete(String publiId) {
		this.publiRepository.delete(publiId);
	}
	
	public List<Publi> findByTitleOrDescriptionLike(String title, String description){
		return this.publiRepository.findByTitleOrDescriptionLike(title, description);
	}
	
	public List<Publi> findByTitle(String title){
		return this.publiRepository.findByTitle(title);
	}
	
	public List<Publi> findByTitleContaining(String title){
		return this.publiRepository.findByTitle(title);
	}
	
	public List<Publi> findByLookupContaining(String lookup){
		return this.publiRepository.findByLookupContaining(lookup);
	}
	
	public List<Publi> findByLookupContainingAndCityContaining(String lookup, String city){
		return this.publiRepository.findByLookupContainingAndCityContaining(lookup, city, pageable);
	}
	
	public List<Publi> findByLookupContainingAndCityContainingPageable(String lookup, String city, Boolean newSearch){
		if(pageable==null)
			pageable = new PageRequest(0,20);
		else
			if(newSearch==true)
				pageable = pageable.first();
			else
				pageable = pageable.next();
		
		return this.publiRepository.findByLookupContainingAndCityContaining(lookup, city, pageable);
	}
}
