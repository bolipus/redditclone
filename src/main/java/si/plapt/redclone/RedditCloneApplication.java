package si.plapt.redclone;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import si.plapt.redclone.config.RedditCloneProperties;

@SpringBootApplication
@EnableConfigurationProperties(RedditCloneProperties.class)
@EnableTransactionManagement
@Slf4j
public class RedditCloneApplication {

	@Autowired
	RedditCloneProperties prop;

	public static void main(String[] args) {
		SpringApplication.run(RedditCloneApplication.class, args);
	}


	@Bean
	@Profile("dev")
	CommandLineRunner runOnly(){
		return (args) -> {
			System.out.println("Only dev.");
		};		
	}

}
