package petadopt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class PetAdoptApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		 SpringApplication.run(PetAdoptApplication.class, args);
	}

}
