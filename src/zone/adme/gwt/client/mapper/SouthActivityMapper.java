package zone.adme.gwt.client.mapper;

import javax.inject.Inject;

import zone.adme.gwt.client.places.BasePlace;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;

public class SouthActivityMapper implements ActivityMapper
{
    @Inject
    SouthPlaceToActivityVisitor visitor;

    @Override
    public Activity getActivity(Place place)
    {
        if (!(place instanceof BasePlace))
            return null;

        return ((BasePlace) place).accept(visitor, null);
    }

}
