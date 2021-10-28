package com.ss.training.utopia.dao;

import com.ss.training.utopia.entity.BookingAgent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgentDao extends JpaRepository<BookingAgent, Integer> {
}
