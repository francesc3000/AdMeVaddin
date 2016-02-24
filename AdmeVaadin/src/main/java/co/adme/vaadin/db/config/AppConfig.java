package co.adme.vaadin.db.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoClientFactoryBean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import com.mongodb.MongoClient;

@Configuration
//@EnableAutoConfiguration
//@ComponentScan
@PropertySource(value = "classpath:mongo-db.properties")
public class AppConfig {

    @Value("${MONGO_DB_HOST}")
    private String MONGO_DB_HOST;
 
    @Value("${MONGO_DB_PORT}")
    private int MONGO_DB_PORT;
 
    @Value("${DB}")
    private String DB;
 
    protected @Bean String getDatabaseName() {
        return DB;
    }
 
    public @Bean MongoClient getMongo() throws Exception {
        return new MongoClient(MONGO_DB_HOST, MONGO_DB_PORT);
    }
 
    public @Bean MongoDbFactory mongoDbFactory() throws Exception {
        return new SimpleMongoDbFactory(getMongo(), getDatabaseName());
    }
 
    public @Bean MongoTemplate mongoTemplate() throws Exception {
        MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory());
 
        return mongoTemplate;
    }
    
    public @Bean MongoClientFactoryBean mongo() {
    	MongoClientFactoryBean mongo = new MongoClientFactoryBean();
        mongo.setHost("MONGO_DB_HOST");
        return mongo;
     }

}
