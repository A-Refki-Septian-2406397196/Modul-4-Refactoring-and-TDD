package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PaymentServiceImplTest {

    @Mock
    private PaymentRepository paymentRepository;

    @InjectMocks
    private PaymentServiceImpl paymentService;

    private Payment payment1;
    private Payment payment2;

    @BeforeEach
    void setUp() {
        Map<String, String> paymentData1 = new HashMap<>();
        paymentData1.put("sampleKey1", "sampleValue1");

        Map<String, String> paymentData2 = new HashMap<>();
        paymentData2.put("sampleKey2", "sampleValue2");

        payment1 = new Payment("1", "Bank Transfer", "SUCCESS", paymentData1);
        payment2 = new Payment("2", "Voucher", "REJECTED", paymentData2);
    }

    @Test
    void testAddPayment() {
        when(paymentRepository.save(payment1)).thenReturn(payment1);

        Payment result = paymentService.addPayment(payment1);

        assertNotNull(result);
        assertEquals(payment1, result);
        verify(paymentRepository, times(1)).save(payment1);
    }

    @Test
    void testGetPaymentById() {
        when(paymentRepository.findById("1")).thenReturn(payment1);

        Payment result = paymentService.getPaymentById("1");

        assertNotNull(result);
        assertEquals(payment1, result);
        verify(paymentRepository, times(1)).findById("1");
    }

    @Test
    void testGetPaymentByIdReturnsNullIfNotFound() {
        when(paymentRepository.findById("999")).thenReturn(null);

        Payment result = paymentService.getPaymentById("999");

        assertNull(result);
        verify(paymentRepository, times(1)).findById("999");
    }

    @Test
    void testGetAllPayments() {
        List<Payment> payments = List.of(payment1, payment2);
        when(paymentRepository.findAll()).thenReturn(payments);

        List<Payment> result = paymentService.getAllPayments();

        assertEquals(2, result.size());
        assertEquals(payments, result);
        verify(paymentRepository, times(1)).findAll();
    }
}