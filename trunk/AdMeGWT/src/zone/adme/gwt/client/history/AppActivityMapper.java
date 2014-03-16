package zone.adme.gwt.client.history;

import zone.adme.gwt.client.ClientFactory;
import zone.adme.gwt.client.activities.MainActivity;
import zone.adme.gwt.client.activities.PControlActivity;
import zone.adme.gwt.client.activities.SignActivity;
import zone.adme.gwt.client.places.MainPlace;
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
		if (place instanceof MainPlace){
			MainActivity mainActivity = new MainActivity((MainPlace) place, clientFactory);
			mainActivity.startHandler();
			return mainActivity;
		}
	    else if (place instanceof SignPlace){
			SignActivity signActivity = new SignActivity((SignPlace) place, clientFactory);
			signActivity.startHandler();
			return signActivity;
		}
		else if(place instanceof PControlPlace){
			PControlActivity pControlActivity =  new PControlActivity((PControlPlace) place, clientFactory);
			pControlActivity.startHandler();
			return pControlActivity;
		}
		return null;
	}

}
