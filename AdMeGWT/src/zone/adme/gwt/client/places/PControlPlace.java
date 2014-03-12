package zone.adme.gwt.client.places;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class PControlPlace extends Place
{
	private String SignName;
	
	public PControlPlace(String token)
	{
		this.SignName = token;
	}

	public String getSignName()
	{
		return SignName;
	}

	public static class Tokenizer implements PlaceTokenizer<PControlPlace>
	{

		@Override
		public String getToken(PControlPlace place)
		{
			return place.getSignName();
		}

		@Override
		public PControlPlace getPlace(String token)
		{
			return new PControlPlace(token);
		}

	}
}