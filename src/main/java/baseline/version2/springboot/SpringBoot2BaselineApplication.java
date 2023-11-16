package baseline.version2.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SpringBoot2BaselineApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBoot2BaselineApplication.class, args);
	}

}
