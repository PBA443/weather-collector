package com.donbhagy.weather_collector.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data // Lombok: Generates getters, setters, toString, equals, hashCode
@NoArgsConstructor // Lombok: Generates no-argument constructor
@AllArgsConstructor // Lombok: Generates constructor with all fields
public class WeatherRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String locationName;
    private String country;
    private double latitude;
    private double longitude;

    private double temperatureCelsius;
    private double temperatureFahrenheit;
    private String conditionText;
    private String conditionIconUrl;
    private double windKph;
    private double pressureMb;
    private double humidity;
    private double feelsLikeCelsius;
    private double feelsLikeFahrenheit;
    private double uvIndex;

    @Temporal(TemporalType.TIMESTAMP) // Maps LocalDateTime to SQL TIMESTAMP
    private LocalDateTime observationTime; // Time of data collection from API

    @Temporal(TemporalType.TIMESTAMP)
    private Date recordTimestamp; // When this record was saved to the DB

    // You can add more fields as needed based on the WeatherAPI.com response

    // Custom constructor for convenience when populating from API
    public WeatherRecord(String locationName, String country, double latitude, double longitude,
                         double temperatureCelsius, double temperatureFahrenheit, String conditionText,
                         String conditionIconUrl, double windKph, double pressureMb, double humidity,
                         double feelsLikeCelsius, double feelsLikeFahrenheit, double uvIndex,
                         LocalDateTime observationTime) {
        this.locationName = locationName;
        this.country = country;
        this.latitude = latitude;
        this.longitude = longitude;
        this.temperatureCelsius = temperatureCelsius;
        this.temperatureFahrenheit = temperatureFahrenheit;
        this.conditionText = conditionText;
        this.conditionIconUrl = conditionIconUrl;
        this.windKph = windKph;
        this.pressureMb = pressureMb;
        this.humidity = humidity;
        this.feelsLikeCelsius = feelsLikeCelsius;
        this.feelsLikeFahrenheit = feelsLikeFahrenheit;
        this.uvIndex = uvIndex;
        this.observationTime = observationTime;
        this.recordTimestamp = new Date(); // Set current time when creating the record
    }
}
