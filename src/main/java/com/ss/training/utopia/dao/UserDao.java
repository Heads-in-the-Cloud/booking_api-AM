package com.ss.training.utopia.dao;

import com.ss.training.utopia.entity.BookingUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<BookingUser, Integer> {
}
