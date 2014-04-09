package zone.adme.gwt.client.mapper;

import javax.inject.Inject;
import javax.inject.Provider;

import zone.adme.gwt.client.activities.SouthActivity;
import zone.adme.gwt.client.places.FormPubliPlace;
import zone.adme.gwt.client.places.ShowPlace;
import zone.adme.gwt.client.places.InitPlace;
import zone.adme.gwt.client.places.PControlPlace;
import zone.adme.gwt.client.places.PlaceVisitor;
import zone.adme.gwt.client.places.RegisterPlace;
import zone.adme.gwt.client.places.SignPlace;

import com.google.gwt.activity.shared.Activity;

public class SouthPlaceToActivityVisitor implements PlaceVisitor<Void, Activity>
{
    @Inject
    Provider<SouthActivity> provider1;
    
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
		return null;
	}

	@Override
	public Activity visit(RegisterPlace registerPlace, Void in) {
		return null;
	}

	@Override
	public Activity visit(ShowPlace showPlace, Void in) {
		return null;
	}

	@Override
	public Activity visit(FormPubliPlace showPlace, Void in) {
		return null;
	}
}
