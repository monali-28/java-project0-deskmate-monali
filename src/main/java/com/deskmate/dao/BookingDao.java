package com.deskmate.dao;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import com.deskmate.constants.BookingStatus;
import com.deskmate.model.Booking;
import com.sun.jdi.connect.spi.Connection;

public interface BookingDao {
	long insertBooking(Connection conn, long deskId, String phone, LocalDateTime start, LocalDateTime end,
			BigDecimal total, BookingStatus status);

	void updateStatus(Connection conn, long bookingId, BookingStatus status);

	Optional<Booking> findById(long bookingId);
}
	