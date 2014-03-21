package zone.adme.gwt.client.places;

import com.google.gwt.place.shared.Place;


public abstract class BasePlace extends Place
{
    public abstract <IN, OUT> OUT accept(PlaceVisitor<IN, OUT> visitor, IN in);
}
