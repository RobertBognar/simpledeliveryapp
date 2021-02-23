package deliveryapp.jwd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.servlet.view.tiles3.SpringWildcardServletTilesApplicationContext;

@SpringBootApplication
public class DeliveryApplication extends SpringBootServletInitializer {
	
	public static void main(String[] args) {
		SpringApplication.run(DeliveryApplication.class, args);
	}

}
