package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Payment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PaymentRepositoryTest {

    private PaymentRepository paymentRepository;
    private Payment payment1;
    private Payment payment2;

    @BeforeEach
    void setUp() {
        paymentRepository = new PaymentRepository();

        Map<String, String> paymentData1 = new HashMap<>();
        paymentData1.put("sampleKey1", "sampleValue1");

        Map<String, String> paymentData2 = new HashMap<>();
        paymentData2.put("sampleKey2", "sampleValue2");

        payment1 = new Payment("1", "Bank Transfer", "SUCCESS", paymentData1);
        payment2 = new Payment("2", "Voucher", "REJECTED", paymentData2);
    }

    @Test
    void testSavePayment() {
        Payment savedPayment = paymentRepository.save(payment1);

        assertEquals(payment1, savedPayment);
        assertEquals(payment1, paymentRepository.findById("1"));
    }

    @Test
    void testFindByIdReturnsPaymentIfExists() {
        paymentRepository.save(payment1);

        Payment foundPayment = paymentRepository.findById("1");

        assertNotNull(foundPayment);
        assertEquals(payment1, foundPayment);
    }

    @Test
    void testFindByIdReturnsNullIfNotExists() {
        Payment foundPayment = paymentRepository.findById("999");

        assertNull(foundPayment);
    }

    @Test
    void testFindAllReturnsEmptyListIfNoPaymentExists() {
        List<Payment> payments = paymentRepository.findAll();

        assertNotNull(payments);
        assertTrue(payments.isEmpty());
    }

    @Test
    void testFindAllReturnsAllSavedPayments() {
        paymentRepository.save(payment1);
        paymentRepository.save(payment2);

        List<Payment> payments = paymentRepository.findAll();

        assertEquals(2, payments.size());
        assertTrue(payments.contains(payment1));
        assertTrue(payments.contains(payment2));
    }
}