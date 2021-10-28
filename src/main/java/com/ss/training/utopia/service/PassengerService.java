package com.ss.training.utopia.service;

import com.ss.training.utopia.Exception.SQLAlreadyExistsException;
import com.ss.training.utopia.Exception.SQLDoesNotExistException;
import com.ss.training.utopia.dao.BookingDao;
import com.ss.training.utopia.dao.PassengerDao;
import com.ss.training.utopia.dto.PassengerDto;
import com.ss.training.utopia.entity.Booking;
import com.ss.training.utopia.entity.Passenger;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PassengerService {

    // vars
    private final PassengerDao dao;
    private final BookingDao bdao;

    /**
     * Constructor
     * @param dao DAO for Jpa operations
     */
    public PassengerService(PassengerDao dao, BookingDao bdao) {
        this.dao = dao;
        this.bdao = bdao;
    }

    /**
     * Convert DTO object to entity
     * @param dto DTO to convert
     * @return Passenger entity
     */
    public Passenger dtoToEntity(PassengerDto dto) {
        // check existing
        Optional<Booking> booking = bdao.findById(dto.getBookingId());
        if (booking.isEmpty())
            throw new SQLDoesNotExistException("Booking", String.valueOf(dto.getBookingId()));

        // create object
        return Passenger.builder()
            .id(dto.getId())
            .bookingId(dto.getBookingId())
            .givenName(dto.getGivenName())
            .familyName(dto.getFamilyName())
            .dob(dto.getDob())
            .gender(dto.getGender())
            .address(dto.getAddress())
            .build();
    }

    public List<Passenger> getAll() {
        return dao.findAll();
    }

    public List<Passenger> getAllByBookingId(Integer id) {
        return dao.findAll(Example.of(Passenger.builder().bookingId(id).build()));
    }

    public Passenger getById(Integer id) {
        Optional<Passenger> passenger = dao.findById(id);
        if (passenger.isEmpty())
            throw new SQLDoesNotExistException("Passenger", String.valueOf(id));
        return passenger.get();
    }

    public Passenger add(PassengerDto insert) {
        Passenger passenger = dtoToEntity(insert);
        if (dao.existsById(insert.getBookingId()))
            throw new SQLAlreadyExistsException("Passenger", String.valueOf(insert.getBookingId()));
        return dao.save(passenger);
    }

    public void update(PassengerDto insert) {
        Passenger passenger = dtoToEntity(insert);
        if (!dao.existsById(passenger.getId()))
            throw new SQLDoesNotExistException("Passenger", String.valueOf(insert.getBookingId()));
        dao.save(passenger);
    }

    public void delete(Integer id) {
        Optional<Passenger> passenger = dao.findById(id);
        if (passenger.isEmpty())
            throw new SQLDoesNotExistException("Passenger", String.valueOf(id));
        dao.delete(passenger.get());
    }
}
