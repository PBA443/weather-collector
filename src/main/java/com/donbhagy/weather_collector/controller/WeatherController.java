package com.donbhagy.weather_collector.controller;

import com.donbhagy.weather_collector.entity.WeatherRecord;
import com.donbhagy.weather_collector.service.WeatherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/weather")
public class WeatherController {

    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/collect")
    public ResponseEntity<String> collectWeather(@RequestParam String location) {
        if (location == null || location.trim().isEmpty()) {
            return new ResponseEntity<>("Location parameter is required.", HttpStatus.BAD_REQUEST);
        }

        WeatherRecord savedRecord = weatherService.fetchAndSaveCurrentWeather(location);

        if (savedRecord != null) {
            return new ResponseEntity<>("Weather data for " + location + " collected and saved successfully. Record ID: " + savedRecord.getId(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to collect or save weather data for " + location + ". Check application logs for details.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
