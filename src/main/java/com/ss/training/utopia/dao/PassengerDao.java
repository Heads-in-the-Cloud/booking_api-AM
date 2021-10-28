package com.ss.training.utopia.dao;

import com.ss.training.utopia.entity.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassengerDao extends JpaRepository<Passenger, Integer> {
}
