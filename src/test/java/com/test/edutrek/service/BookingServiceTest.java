package com.test.edutrek.service;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import com.deskmate.constants.PaymentMode;

import com.deskmate.exception.ValidationException;
import com.deskmate.services.BookingService;
import com.test.edutrek.service.fake.FakeDaos;

public class BookingServiceTest {

    @Test
    void shouldRejectInactiveDeskBooking() {
        var deskDao = new FakeDaos.FakeDeskDao().seedDesk("D-101", false);
        var bookingDao = new FakeDaos.FakeBookingDao();
        var paymentDao = new FakeDaos.FakePaymentDao();
        var svc = new BookingService(deskDao, bookingDao, paymentDao);

        assertThrows(ValidationException.class, () -> svc.createBookingWithPayment(
                "D-101", "9999999999",
                LocalDateTime.now().plusHours(1),
                LocalDateTime.now().plusHours(2),
                new BigDecimal("100.00"),
                PaymentMode.CASH,
                new BigDecimal("100.00")
        ));
    }

    @Test
    void shouldFailPaymentWhenAmountMismatch() {
        var deskDao = new FakeDaos.FakeDeskDao().seedDesk("D-101", true);
        var svc = new BookingService(deskDao, new FakeDaos.FakeBookingDao(), new FakeDaos.FakePaymentDao());

        assertThrows(ValidationException.class, () -> svc.createBookingWithPayment(
                "D-101", "9999999999",
                LocalDateTime.now().plusHours(1),
                LocalDateTime.now().plusHours(2),
                new BigDecimal("100.00"),
                PaymentMode.CARD,
                new BigDecimal("90.00")
        ));
    }
}
