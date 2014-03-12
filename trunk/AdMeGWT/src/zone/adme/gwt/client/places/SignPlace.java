package zone.adme.gwt.client.places;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class SignPlace extends Place
{
	private String SignName;
	
	public SignPlace(String token)
	{
		this.SignName = token;
	}

	public String getSignName()
	{
		return SignName;
	}

	public static class Tokenizer implements PlaceTokenizer<SignPlace>
	{

		@Override
		public String getToken(SignPlace place)
		{
			return place.getSignName();
		}

		@Override
		public SignPlace getPlace(String token)
		{
			return new SignPlace(token);
		}

	}
}