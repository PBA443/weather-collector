package com.donbhagy.weather_collector.repository;

import com.donbhagy.weather_collector.entity.WeatherRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherRecordRepository extends JpaRepository<WeatherRecord, Long> {
    // Spring Data JPA provides basic CRUD operations automatically (save, findById, findAll, delete etc.)
    // You can add custom query methods here if needed, e.g., findByLocationName(String locationName);
}
