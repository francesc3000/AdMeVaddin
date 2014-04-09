package zone.adme.gwt.client.places;

import com.google.gwt.place.shared.PlaceTokenizer;

public class FormPubliPlace extends BasePlace
{
	private String formPubliName;
	
	public FormPubliPlace(String token)
	{
		this.formPubliName = token;
	}

	public String getFormPubliName()
	{
		return formPubliName;
	}

	public static class Tokenizer implements PlaceTokenizer<FormPubliPlace>
	{

		@Override
		public String getToken(FormPubliPlace place)
		{
			return place.getFormPubliName();
		}

		@Override
		public FormPubliPlace getPlace(String token)
		{
			return new FormPubliPlace(token);
		}

	}

	@Override
	public <IN, OUT> OUT accept(PlaceVisitor<IN, OUT> visitor, IN in) {
		return visitor.visit(this, in);
	}
}