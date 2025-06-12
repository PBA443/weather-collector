package com.donbhagy.weather_collector.service;

import com.donbhagy.weather_collector.entity.WeatherRecord;
import com.donbhagy.weather_collector.model.WeatherApiResponse;
import com.donbhagy.weather_collector.repository.WeatherRecordRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class WeatherService {

    @Value("${weatherapi.key}")
    private String apiKey;

    @Value("${weatherapi.base-url}")
    private String baseUrl;

    @Value("${weatherapi.current-endpoint}")
    private String currentEndpoint;

    private final RestTemplate restTemplate;
    private final WeatherRecordRepository weatherRecordRepository;

    public WeatherService(RestTemplate restTemplate, WeatherRecordRepository weatherRecordRepository) {
        this.restTemplate = restTemplate;
        this.weatherRecordRepository = weatherRecordRepository;
    }

    public WeatherRecord fetchAndSaveCurrentWeather(String locationQuery) {
        // Build the URL for the WeatherAPI.com current weather endpoint
        String url = UriComponentsBuilder.fromHttpUrl(baseUrl + currentEndpoint)
                .queryParam("key", apiKey)
                .queryParam("q", locationQuery)
                .build()
                .toUriString();

        try {
            // Make the API call and get the response
            WeatherApiResponse apiResponse = restTemplate.getForObject(url, WeatherApiResponse.class);

            if (apiResponse != null && apiResponse.getLocation() != null && apiResponse.getCurrent() != null) {
                // Parse the observation time string from the API
                // Example format from API: "2023-04-23 10:00"
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd H:mm");
                LocalDateTime observationDateTime = LocalDateTime.parse(apiResponse.getCurrent().getLastUpdated(), formatter);

                // Create a WeatherRecord entity from the API response
                WeatherRecord weatherRecord = new WeatherRecord(
                        apiResponse.getLocation().getName(),
                        apiResponse.getLocation().getCountry(),
                        apiResponse.getLocation().getLat(),
                        apiResponse.getLocation().getLon(),
                        apiResponse.getCurrent().getTempC(),
                        apiResponse.getCurrent().getTempF(),
                        apiResponse.getCurrent().getCondition().getText(),
                        apiResponse.getCurrent().getCondition().getIcon(),
                        apiResponse.getCurrent().getWindKph(),
                        apiResponse.getCurrent().getPressureMb(),
                        apiResponse.getCurrent().getHumidity(),
                        apiResponse.getCurrent().getFeelslikeC(),
                        apiResponse.getCurrent().getFeelslikeF(),
                        apiResponse.getCurrent().getUv(),
                        observationDateTime
                );

                // Save the record to the database
                return weatherRecordRepository.save(weatherRecord);
            }
        } catch (Exception e) {
            System.err.println("Error fetching or saving weather data for " + locationQuery + ": " + e.getMessage());
            // In a real application, you'd use a proper logging framework (e.g., SLF4J with Logback)
            // and potentially throw a custom exception or handle it more gracefully.
        }
        return null;
    }
}
