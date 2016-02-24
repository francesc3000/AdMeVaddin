package co.adme.vaadin.db;

import org.springframework.data.mongodb.repository.MongoRepository;

import co.adme.vaadin.modelo.User;
public interface UserRepository extends MongoRepository<User, String> {
	public User findByEmail(String email);
}
