package zone.adme.gwt.client.places;

import com.google.gwt.place.shared.PlaceTokenizer;

public class PControlPlace extends BasePlace
{
	private String pControlName;
	
	public PControlPlace(String token)
	{
		this.pControlName = token;
	}

	public String getPControlName()
	{
		return pControlName;
	}

	public static class Tokenizer implements PlaceTokenizer<PControlPlace>
	{

		@Override
		public String getToken(PControlPlace place)
		{
			return place.getPControlName();
		}

		@Override
		public PControlPlace getPlace(String token)
		{
			return new PControlPlace(token);
		}

	}

	@Override
	public <IN, OUT> OUT accept(PlaceVisitor<IN, OUT> visitor, IN in) {
		return visitor.visit(this, in);
	}
}