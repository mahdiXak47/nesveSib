package NesveSib.Installment;

import NesveSib.Installment.utils.ProjectInternalTools;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication
@SpringBootApplication(scanBasePackages = "NesveSib.Installment")
public class IseApplication {

	public static void main(String[] args) {
		ProjectInternalTools.logger.info("starting project");
		SpringApplication.run(IseApplication.class, args);
	}

}
