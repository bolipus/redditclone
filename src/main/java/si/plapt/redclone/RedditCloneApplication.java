package si.plapt.redclone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import si.plapt.redclone.config.RedditCloneProperties;

@SpringBootApplication
@EnableConfigurationProperties(RedditCloneProperties.class)
public class RedditCloneApplication {

	@Autowired
	RedditCloneProperties prop;

	public static void main(String[] args) {
		SpringApplication.run(RedditCloneApplication.class, args);
	}

	@Bean
	CommandLineRunner welcome(){
		return (args) -> {
			System.out.println("Welcome message:" + prop.getWelcomeMessage());
		};		
	}

	@Bean
	@Profile("dev")
	CommandLineRunner runOnly(){
		return (args) -> {
			System.out.println("Only dev.");
		};		
	}

}
