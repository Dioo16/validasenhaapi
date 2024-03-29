package casevalidasenha.validasenhapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
@EnableSwagger2
public class ValidasenhapiApplication {
	public static void main(String[] args) {
		SpringApplication.run(ValidasenhapiApplication.class, args);
	}
}
