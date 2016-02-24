package co.adme.vaadin.db;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import co.adme.vaadin.modelo.Publi;

public interface PubliRepository extends MongoRepository<Publi, String> {
	public List<Publi> findByTitleOrDescriptionLike(String title, String description);
	public List<Publi> findByTitle(String title);
	public List<Publi> findByTitleContaining(String title);
	public List<Publi> findByLookupContaining(String lookup);
	public List<Publi> findByLookupContainingAndCityContaining(String lookup, String city);
	public List<Publi> findByLookupContainingAndCityContaining(String lookup, String city, Pageable pageable);
}
