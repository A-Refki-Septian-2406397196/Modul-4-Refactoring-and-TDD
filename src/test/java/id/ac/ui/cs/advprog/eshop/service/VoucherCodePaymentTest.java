package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class VoucherCodePaymentTest {

    private Map<String, String> paymentData;

    @BeforeEach
    void setUp() {
        paymentData = new HashMap<>();
    }

    @Test
    void testCreateVoucherPaymentSuccess() {
        paymentData.put("voucherCode", "ESHOP1234ABC5678");

        VoucherCodePayment payment = new VoucherCodePayment("1", paymentData);

        assertEquals("1", payment.getId());
        assertEquals("Voucher Code", payment.getMethod());
        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
        assertEquals("ESHOP1234ABC5678", payment.getPaymentData().get("voucherCode"));
    }

    @Test
    void testCreateVoucherPaymentRejectedIfLengthIsNotSixteen() {
        paymentData.put("voucherCode", "ESHOP123ABC5678");

        VoucherCodePayment payment = new VoucherCodePayment("1", paymentData);

        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }

    @Test
    void testCreateVoucherPaymentRejectedIfPrefixIsNotEshop() {
        paymentData.put("voucherCode", "TOKOP1234ABC5678");

        VoucherCodePayment payment = new VoucherCodePayment("1", paymentData);

        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }

    @Test
    void testCreateVoucherPaymentRejectedIfDoesNotContainEightDigits() {
        paymentData.put("voucherCode", "ESHOPABCDABC5678");

        VoucherCodePayment payment = new VoucherCodePayment("1", paymentData);

        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }

    @Test
    void testCreateVoucherPaymentRejectedIfVoucherCodeIsNull() {
        paymentData.put("voucherCode", null);

        VoucherCodePayment payment = new VoucherCodePayment("1", paymentData);

        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }
}