package co.adme.vaadin.modelo;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.ViewScope;

@SpringComponent
@ViewScope
public class Rating {
	
	double rating;
	List<String> raterList = new ArrayList<String>();
	
	double getRating(){
		double rating;
		
		rating = this.rating;
		if(raterList.size()==0)
			rating = 5;
		
		return rating;
	}
	
	void addRating(Modelo rater, double rating){
		this.raterList.add(rater.getId());
		int totalRaters = this.raterList.size();
		this.rating = (this.rating + rating ) / totalRaters;
	}
	
	Boolean isRater(Modelo rater){
		return this.raterList.contains(rater.getId());
	}
}
