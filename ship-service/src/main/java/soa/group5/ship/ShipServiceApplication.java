package soa.group5.ship;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ShipServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShipServiceApplication.class, args);
	}

}
