package com.ss.training.utopia.dao;

import com.ss.training.utopia.entity.BookingGuest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestDao extends JpaRepository<BookingGuest, Integer> {
}
