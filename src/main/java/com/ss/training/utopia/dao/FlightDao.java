package com.ss.training.utopia.dao;

import com.ss.training.utopia.entity.BookingFlight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightDao extends JpaRepository<BookingFlight, Integer> {
}
