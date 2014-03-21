package zone.adme.gwt.client.places;

import com.google.gwt.place.shared.PlaceTokenizer;

public class RegisterPlace extends BasePlace
{
    private final String registerPlace;

    public RegisterPlace(String paramPlace1)
    {
        this.registerPlace = paramPlace1;
    }

    public String getParamPlace1()
    {
        return registerPlace;
    }

    public static class Tokenizer implements PlaceTokenizer<RegisterPlace> {
        @Override
        public String getToken(RegisterPlace place) {
            return place.getParamPlace1();
        }

        @Override
        public RegisterPlace getPlace(String token) {
            return new RegisterPlace(token);
        }
    }

    @Override
    public <IN, OUT> OUT accept(PlaceVisitor<IN, OUT> visitor, IN in)
    {
        return visitor.visit(this, in);
    }
}
