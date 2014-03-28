package zone.adme.gwt.client.mapper;

import zone.adme.gwt.client.places.InitPlace;
import zone.adme.gwt.client.places.PControlPlace;
import zone.adme.gwt.client.places.RegisterPlace;
import zone.adme.gwt.client.places.ShowPlace;
import zone.adme.gwt.client.places.SignPlace;

import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.place.shared.WithTokenizers;

/**
 * PlaceHistoryMapper interface is used to attach all places which the
 * PlaceHistoryHandler should be aware of. This is done via the @WithTokenizers
 * annotation or by extending PlaceHistoryMapperWithFactory and creating a
 * separate TokenizerFactory.
 */
@WithTokenizers( {  InitPlace.Tokenizer.class
					,SignPlace.Tokenizer.class
					,PControlPlace.Tokenizer.class
					,RegisterPlace.Tokenizer.class
					,ShowPlace.Tokenizer.class
				 })
public interface AppPlaceHistoryMapper extends PlaceHistoryMapper {
}
