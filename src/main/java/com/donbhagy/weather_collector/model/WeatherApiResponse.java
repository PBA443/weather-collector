package com.donbhagy.weather_collector.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

// Top-level response object
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WeatherApiResponse {
    private Location location;
    private Current current;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Location {
        private String name;
        private String region;
        private String country;
        private double lat;
        private double lon;
        @JsonProperty("tz_id")
        private String tzId;
        @JsonProperty("localtime_epoch")
        private long localtimeEpoch;
        private String localtime;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Current {
        @JsonProperty("last_updated_epoch")
        private long lastUpdatedEpoch;
        @JsonProperty("last_updated")
        private String lastUpdated;
        @JsonProperty("temp_c")
        private double tempC;
        @JsonProperty("temp_f")
        private double tempF;
        @JsonProperty("is_day")
        private int isDay; // 1 for day, 0 for night
        private Condition condition;
        @JsonProperty("wind_kph")
        private double windKph;
        @JsonProperty("wind_mph")
        private double windMph;
        @JsonProperty("pressure_mb")
        private double pressureMb;
        @JsonProperty("pressure_in")
        private double pressureIn;
        @JsonProperty("humidity")
        private double humidity;
        @JsonProperty("cloud")
        private int cloud; // Cloud cover as percentage
        @JsonProperty("feelslike_c")
        private double feelslikeC;
        @JsonProperty("feelslike_f")
        private double feelslikeF;
        @JsonProperty("uv")
        private double uv; // UV index
        // ... potentially more fields
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Condition {
        private String text;
        private String icon;
        private int code;
    }
}
