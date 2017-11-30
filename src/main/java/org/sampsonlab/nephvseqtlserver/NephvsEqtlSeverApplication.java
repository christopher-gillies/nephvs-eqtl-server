package org.sampsonlab.nephvseqtlserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class NephvsEqtlSeverApplication {

	public static void main(String[] args) {
		SpringApplication.run(NephvsEqtlSeverApplication.class, args);
	}
}
