package com.ss.training.utopia.dao;

import com.ss.training.utopia.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingDao extends JpaRepository<Booking, Integer> {

}
