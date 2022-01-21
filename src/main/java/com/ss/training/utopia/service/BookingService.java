package com.ss.training.utopia.service;

import com.ss.training.utopia.Exception.SQLDoesNotExistException;
import com.ss.training.utopia.Exception.SQLInvalidInputException;
import com.ss.training.utopia.dao.*;
import com.ss.training.utopia.dto.BookingDto;
import com.ss.training.utopia.dto.BookingObjects;
import com.ss.training.utopia.entity.*;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    // vars
    private final BookingDao dao;
    private final FlightDao fdao;
    private final UserDao udao;
    private final AgentDao adao;
    private final GuestDao gdao;
    private final String objectType;

    /**
     * Constructor
     * @param dao DAO to use for Bookings
     */
    public BookingService(BookingDao dao, FlightDao fdao, UserDao udao, AgentDao adao, GuestDao gdao) {
        this.dao = dao;
        this.fdao = fdao;
        this.udao = udao;
        this.adao = adao;
        this.gdao = gdao;
        objectType = "Booking";
    }

    /**
     * Helper method to guarantee and differentiate user type
     * @param user nullable user reference
     * @param guest nullable guest reference (email)
     * @return type of user used, or null if error/both
     */
    private Integer getUserType(Integer user, String guest) {
        int count = 0, type = 0;
        if (user != null) {
            count++;
            type = 1;
        }
        if (guest != null) {
            count++;
            type = 2;
        }
        if (count == 1) return type;
        return null;
    }

    /**
     * Convert DTO objects to entity collection
     * @param dto DTO to convert
     * @return resulting Booking entities
     */
    public BookingObjects dtoToCollection(BookingDto dto) {
        // check existing portions
        Integer userType = getUserType(dto.getUserId(), dto.getGuestEmail());
        if (userType == null)
            throw new IllegalArgumentException();

        // create initial booking
        Booking booking = Booking.builder()
            .isActive(dto.getIsActive())
            .confirm(dto.getConfirmationCode())
            .build();
        booking = dao.save(booking);

        // create correct user object(s)
        BookingUser user = null;
        BookingGuest guest = null;
        switch (userType) {
            case 1:
                user = BookingUser.builder().id(BookingUserPK.builder()
                    .bookingId(booking.getId()).userId(dto.getUserId()).build()).build();
            case 2:
                guest = BookingGuest.builder().bookingId(booking.getId())
                    .email(dto.getGuestEmail()).phone(dto.getGuestPhone()).build();
            default:
                break;
        }

        // create assigned agent object
        BookingAgent agent = BookingAgent.builder().id(BookingAgentPK.builder()
            .bookingId(booking.getId()).agentId(dto.getAgentId()).build()).build();

        // create correct flight object
        BookingFlight flight = BookingFlight.builder().id(BookingFlightPK.builder()
            .bookingId(booking.getId()).flightId(dto.getFlightId()).build()).build();

        // build objects
        return BookingObjects.builder()
            .booking(booking)
            .bookingAgent(agent)
            .bookingFlight(flight)
            .bookingGuest(guest)
            .bookingUser(user)
            .build();
    }

    public List<Booking> getAll() {
        return dao.findAll();
    }

    public Booking getById(Integer id) {
        Optional<Booking> booking = dao.findById(id);
        if (booking.isEmpty())
            throw new SQLDoesNotExistException(objectType, String.valueOf(id));
        return booking.get();
    }

    @Transactional(rollbackOn = Exception.class)
    public Booking add(BookingDto insert) {
        BookingObjects objects = dtoToCollection(insert);
        if (insert.getId() != null)
            throw new SQLInvalidInputException(objectType);
        if (objects.getBookingUser() != null) udao.save(objects.getBookingUser());
        else if (objects.getBookingGuest() != null) gdao.save(objects.getBookingGuest());
        fdao.save(objects.getBookingFlight());
        adao.save(objects.getBookingAgent());
        return objects.getBooking();
    }

    @Transactional(rollbackOn = Exception.class)
    public void update(BookingDto insert) {
        BookingObjects objects = dtoToCollection(insert);
        if (!dao.existsById(insert.getId()))
            throw new SQLDoesNotExistException(objectType, String.valueOf(insert.getId()));
        dao.save(objects.getBooking());
        if (objects.getBookingUser() != null) udao.save(objects.getBookingUser());
        else if (objects.getBookingGuest() != null) gdao.save(objects.getBookingGuest());
        adao.save(objects.getBookingAgent());
        fdao.save(objects.getBookingFlight());
    }

    public void delete(Integer id) {
        Optional<Booking> booking = dao.findById(id);
        if (booking.isEmpty())
            throw new SQLDoesNotExistException(objectType, String.valueOf(id));
        dao.delete(booking.get());
    }
}
