package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PaymentTest {

    private Map<String, String> paymentData;

    @BeforeEach
    void setUp() {
        paymentData = new HashMap<>();
        paymentData.put("sampleKey", "sampleValue");
    }

    @Test
    void testCreatePaymentSuccess() {
        Payment payment = new Payment("1", "Bank Transfer", "SUCCESS", paymentData);

        assertEquals("1", payment.getId());
        assertEquals("Bank Transfer", payment.getMethod());
        assertEquals("SUCCESS", payment.getStatus());
        assertEquals(paymentData, payment.getPaymentData());
    }

    @Test
    void testSetStatusWithValidStatus() {
        Payment payment = new Payment("1", "Bank Transfer", "SUCCESS", paymentData);

        payment.setStatus("REJECTED");

        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testCreatePaymentWithInvalidStatusThrowsException() {
        assertThrows(IllegalArgumentException.class, () ->
                new Payment("1", "Bank Transfer", "INVALID", paymentData)
        );
    }

    @Test
    void testSetStatusWithInvalidStatusThrowsException() {
        Payment payment = new Payment("1", "Bank Transfer", "SUCCESS", paymentData);

        assertThrows(IllegalArgumentException.class, () ->
                payment.setStatus("INVALID")
        );
    }
}