package tp.appliSpring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppliSpringBoot2Application {

	public static void main(String[] args) {
		SpringApplication.run(AppliSpringBoot2Application.class, args);
		System.out.println("http://localhost:8080/appliSpringBoot");
	}

}
