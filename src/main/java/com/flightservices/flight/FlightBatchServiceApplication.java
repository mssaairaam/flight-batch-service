package com.flightservices.flight;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class FlightBatchServiceApplication extends SpringBootServletInitializer {

	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(FlightBatchServiceApplication.class);
    }
	
	public static void main(String[] args) {
		SpringApplication.run(FlightBatchServiceApplication.class, args);
	}

}

