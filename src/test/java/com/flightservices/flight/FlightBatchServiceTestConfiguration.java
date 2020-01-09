package com.flightservices.flight;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import com.flightservices.flight.repositories.FlightBatchRepository;


@Profile("test")
@Configuration
public class FlightBatchServiceTestConfiguration {

	@Bean
	@Primary
	public FlightBatchRepository flightBatchRepository() {
		return Mockito.mock(FlightBatchRepository.class);
	}
}
