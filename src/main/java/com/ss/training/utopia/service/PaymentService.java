package com.ss.training.utopia.service;

import com.ss.training.utopia.Exception.SQLAlreadyExistsException;
import com.ss.training.utopia.Exception.SQLDoesNotExistException;
import com.ss.training.utopia.dao.BookingDao;
import com.ss.training.utopia.dao.PaymentDao;
import com.ss.training.utopia.dto.PaymentDto;
import com.ss.training.utopia.entity.Booking;
import com.ss.training.utopia.entity.Payment;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {

    // vars
    private final PaymentDao dao;
    private final BookingDao bdao;
    private final String objectType;

    /**
     * Constructor
     * @param dao DAO for Jpa operations
     */
    public PaymentService(PaymentDao dao, BookingDao bdao) {
        this.dao = dao;
        this.bdao = bdao;
        objectType = "Payment";
    }

    /**
     * Convert DTO object to entity
     * @param dto DTO to convert
     * @return Payment entity
     */
    public Payment dtoToEntity(PaymentDto dto) {
        // check existing
        Optional<Booking> booking = bdao.findById(dto.getBookingId());
        if (booking.isEmpty())
            throw new SQLDoesNotExistException("Booking", String.valueOf(dto.getBookingId()));

        // create object
        return Payment.builder()
            .booking(dto.getBookingId())
            .refunded(dto.getRefunded())
            .stripeId(dto.getStripeId())
            .build();
    }

    public List<Payment> getAll() {
        return dao.findAll();
    }

    public Payment getById(Integer id) {
        Optional<Payment> payment = dao.findById(id);
        if (payment.isEmpty())
            throw new SQLDoesNotExistException(objectType, String.valueOf(id));
        return payment.get();
    }

    public Payment add(PaymentDto insert) {
        Payment payment = dtoToEntity(insert);
        if (dao.existsById(insert.getBookingId()))
            throw new SQLAlreadyExistsException(objectType, String.valueOf(insert.getBookingId()));
        return dao.save(payment);
    }

    public void update(PaymentDto insert) {
        Payment payment = dtoToEntity(insert);
        if (!dao.existsById(payment.getBooking()))
            throw new SQLDoesNotExistException(objectType, String.valueOf(insert.getBookingId()));
        dao.save(payment);
    }

    public void delete(Integer id) {
        Optional<Payment> payment = dao.findById(id);
        if (payment.isEmpty())
            throw new SQLDoesNotExistException(objectType, String.valueOf(id));
        dao.delete(payment.get());
    }
}
