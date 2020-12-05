package com.hcl.nxp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.hcl.nxp.repository")
public class NXPApplication {

	public static void main(final String[] args) {
		SpringApplication.run(NXPApplication.class, args);
	}

}
