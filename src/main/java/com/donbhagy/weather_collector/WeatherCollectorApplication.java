package com.donbhagy.weather_collector;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class WeatherCollectorApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeatherCollectorApplication.class, args);
	}

	@Bean // Configure RestTemplate to make HTTP calls
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
