package zone.adme.gwt.client.places;

import com.google.gwt.place.shared.PlaceTokenizer;

public class ShowPlace extends BasePlace
{
    private final String initPlace;

    public ShowPlace(String paramPlace1)
    {
        this.initPlace = paramPlace1;
    }

    public String getParamPlace1()
    {
        return initPlace;
    }

    public static class Tokenizer implements PlaceTokenizer<ShowPlace> {
        @Override
        public String getToken(ShowPlace place) {
            return place.getParamPlace1();
        }

        @Override
        public ShowPlace getPlace(String token) {
            return new ShowPlace(token);
        }
    }

    @Override
    public <IN, OUT> OUT accept(PlaceVisitor<IN, OUT> visitor, IN in)
    {
        return visitor.visit(this, in);
    }
}
