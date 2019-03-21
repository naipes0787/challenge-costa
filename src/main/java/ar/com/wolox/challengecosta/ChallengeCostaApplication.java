package ar.com.wolox.challengecosta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class ChallengeCostaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChallengeCostaApplication.class, args);
	}

}
