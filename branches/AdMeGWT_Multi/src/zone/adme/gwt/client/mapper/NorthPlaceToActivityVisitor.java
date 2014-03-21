package zone.adme.gwt.client.mapper;

import javax.inject.Inject;
import javax.inject.Provider;

import zone.adme.gwt.client.activities.NorthSingletonActivity;
import zone.adme.gwt.client.activities.SignActivity;
import zone.adme.gwt.client.places.InitPlace;
import zone.adme.gwt.client.places.PControlPlace;
import zone.adme.gwt.client.places.PlaceVisitor;
import zone.adme.gwt.client.places.RegisterPlace;
import zone.adme.gwt.client.places.SignPlace;

import com.google.gwt.activity.shared.Activity;

public class NorthPlaceToActivityVisitor implements PlaceVisitor<Void, Activity>
{
    @Inject
    Provider<NorthSingletonActivity> provider1;
    @Inject
    Provider<SignActivity> signProvider;
    
    @Override
	public Activity visit(InitPlace initPlace, Void in) {
		return signProvider.get();
	}

	@Override
	public Activity visit(SignPlace signPlace, Void in) {
		return signProvider.get();
	}

	@Override
	public Activity visit(PControlPlace pControlPlace, Void in) {
		return signProvider.get();
	}

	@Override
	public Activity visit(RegisterPlace registerPlace, Void in) {
		return signProvider.get();
	}
}
