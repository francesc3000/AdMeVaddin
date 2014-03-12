package zone.adme.gwt.client.history;

import zone.adme.gwt.client.ClientFactory;
import zone.adme.gwt.client.activities.PControlActivity;
import zone.adme.gwt.client.activities.SignActivity;
import zone.adme.gwt.client.places.PControlPlace;
import zone.adme.gwt.client.places.SignPlace;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;

public class AppActivityMapper implements ActivityMapper {

	private ClientFactory clientFactory;

	/**
	 * AppActivityMapper associates each Place with its corresponding
	 * {@link Activity}
	 * 
	 * @param clientFactory
	 *            Factory to be passed to activities
	 */
	public AppActivityMapper(ClientFactory clientFactory) {
		super();
		this.clientFactory = clientFactory;
	}

	/**
	 * Map each Place to its corresponding Activity. This would be a great use
	 * for GIN.
	 */
	@Override
	public Activity getActivity(Place place) {
		// This is begging for GIN
		if (place instanceof SignPlace){
			return new SignActivity((SignPlace) place, clientFactory);
		}
		else if(place instanceof PControlPlace){
			return new PControlActivity((PControlPlace) place, clientFactory);
		}
		return null;
	}

}
