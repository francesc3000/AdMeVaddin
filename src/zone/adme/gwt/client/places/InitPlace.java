package zone.adme.gwt.client.places;

import com.google.gwt.place.shared.PlaceTokenizer;

public class InitPlace extends BasePlace
{
    private final String initPlace;

    public InitPlace(String paramPlace1)
    {
        this.initPlace = paramPlace1;
    }

    public String getParamPlace1()
    {
        return initPlace;
    }

    public static class Tokenizer implements PlaceTokenizer<InitPlace> {
        @Override
        public String getToken(InitPlace place) {
            return place.getParamPlace1();
        }

        @Override
        public InitPlace getPlace(String token) {
            return new InitPlace(token);
        }
    }

    @Override
    public <IN, OUT> OUT accept(PlaceVisitor<IN, OUT> visitor, IN in)
    {
        return visitor.visit(this, in);
    }
}
