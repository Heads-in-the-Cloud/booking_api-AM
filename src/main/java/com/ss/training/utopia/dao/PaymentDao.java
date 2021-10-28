package com.ss.training.utopia.dao;

import com.ss.training.utopia.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentDao extends JpaRepository<Payment, Integer> {
}
