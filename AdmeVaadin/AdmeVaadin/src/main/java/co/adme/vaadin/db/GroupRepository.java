package co.adme.vaadin.db;

import org.springframework.data.mongodb.repository.MongoRepository;

import co.adme.vaadin.modelo.Group;


public interface GroupRepository extends MongoRepository<Group, String> {

}
