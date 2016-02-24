package co.adme.vaadin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.vaadin.spring.events.annotation.EnableVaadinEventBus;

import com.vaadin.spring.internal.VaadinSessionScope;

@SpringBootApplication
@Configuration
@EnableAutoConfiguration
@ComponentScan//(basePackages={"co.adme.vaadin"})
@EnableMongoRepositories//(basePackages={"co.adme.vaadin"})
@EnableVaadinEventBus
@PropertySource(value = "classpath:mongo-db.properties")
public class AdMeVaadinApplication {
	
	@Bean
	static VaadinSessionScope vaadinSessionScope() {
		return new VaadinSessionScope();
	}

    public static void main(String[] args) {
        SpringApplication.run(AdMeVaadinApplication.class, args);
    }
}
