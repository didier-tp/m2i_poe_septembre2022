package tp.appliSpring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class AppliSpringBootApplication {

	public static void main(String[] args) {
		//SpringApplication.run(AppliSpringBootApplication.class, args);
		SpringApplication app = new SpringApplication(AppliSpringBootApplication.class);
		//app.setAdditionalProfiles("dev");
		app.setAdditionalProfiles("dev","withSecurity");
		//app.setAdditionalProfiles("prod");
		ConfigurableApplicationContext context = app.run(args);
		//context.getBean("...")
		System.out.println("http://localhost:8080/appliSpringBoot");
	}

}
