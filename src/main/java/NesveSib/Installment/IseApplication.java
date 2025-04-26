package NesveSib.Installment;

import NesveSib.Installment.utils.ProjectInternalTools;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication
@SpringBootApplication(scanBasePackages = "NesveSib.Installment")
public class IseApplication {

	public static void main(String[] args) {
		System.out.println("hello azizm!");
		ProjectInternalTools.getLogger(IseApplication.class.getName()).info("starting project");
		SpringApplication.run(IseApplication.class, args);
	}

}
