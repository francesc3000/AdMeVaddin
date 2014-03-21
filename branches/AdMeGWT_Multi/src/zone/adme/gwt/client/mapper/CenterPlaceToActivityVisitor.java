package zone.adme.gwt.client.mapper;

import javax.inject.Inject;
import javax.inject.Provider;

import zone.adme.gwt.client.activities.PControlActivity;
import zone.adme.gwt.client.activities.RegisterActivity;
import zone.adme.gwt.client.places.InitPlace;
import zone.adme.gwt.client.places.PControlPlace;
import zone.adme.gwt.client.places.PlaceVisitor;
import zone.adme.gwt.client.places.RegisterPlace;
import zone.adme.gwt.client.places.SignPlace;

import com.google.gwt.activity.shared.Activity;

public class CenterPlaceToActivityVisitor implements PlaceVisitor<Void, Activity>
{
    @Inject
    Provider<RegisterActivity> registerProvider;
    
    @Inject
    Provider<PControlActivity> pControlProvider;
    
    @Override
	public Activity visit(InitPlace initPlace, Void in) {
		return null;
	}

	@Override
	public Activity visit(SignPlace signPlace, Void in) {
		return null;
	}

	@Override
	public Activity visit(PControlPlace pControlPlace, Void in) {
		return pControlProvider.get();
	}

	@Override
	public Activity visit(RegisterPlace registerPlace, Void in) {
		return registerProvider.get();
	}
}
