package com.deskmate.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.time.LocalDateTime;
import java.util.Optional;

import com.deskmate.constants.BookingStatus;
import com.deskmate.model.Booking;
import com.deskmate.model.Payment;

public interface PaymentDao {
    long insertPayment(Connection conn, Payment payment);
    Optional<Payment> findByBookingId(long bookingId);
}
	
