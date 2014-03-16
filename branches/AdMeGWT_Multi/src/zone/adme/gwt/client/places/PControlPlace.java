package zone.adme.gwt.client.places;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class PControlPlace extends Place
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
}