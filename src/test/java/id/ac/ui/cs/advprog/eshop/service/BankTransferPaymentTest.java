package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class BankTransferPaymentTest {

    private Map<String, String> paymentData;

    @BeforeEach
    void setUp() {
        paymentData = new HashMap<>();
    }

    @Test
    void testCreateBankTransferPaymentSuccess() {
        paymentData.put("bankName", "BCA");
        paymentData.put("referenceCode", "REF123456");

        BankTransferPayment payment = new BankTransferPayment("1", paymentData);

        assertEquals("1", payment.getId());
        assertEquals("Bank Transfer", payment.getMethod());
        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
        assertEquals("BCA", payment.getPaymentData().get("bankName"));
        assertEquals("REF123456", payment.getPaymentData().get("referenceCode"));
    }

    @Test
    void testCreateBankTransferPaymentRejectedIfBankNameNull() {
        paymentData.put("bankName", null);
        paymentData.put("referenceCode", "REF123456");

        BankTransferPayment payment = new BankTransferPayment("1", paymentData);

        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }

    @Test
    void testCreateBankTransferPaymentRejectedIfBankNameEmpty() {
        paymentData.put("bankName", "");
        paymentData.put("referenceCode", "REF123456");

        BankTransferPayment payment = new BankTransferPayment("1", paymentData);

        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }

    @Test
    void testCreateBankTransferPaymentRejectedIfReferenceCodeNull() {
        paymentData.put("bankName", "BCA");
        paymentData.put("referenceCode", null);

        BankTransferPayment payment = new BankTransferPayment("1", paymentData);

        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }

    @Test
    void testCreateBankTransferPaymentRejectedIfReferenceCodeEmpty() {
        paymentData.put("bankName", "BCA");
        paymentData.put("referenceCode", "");

        BankTransferPayment payment = new BankTransferPayment("1", paymentData);

        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }

    @Test
    void testCreateBankTransferPaymentRejectedIfBothEmpty() {
        paymentData.put("bankName", "");
        paymentData.put("referenceCode", "");

        BankTransferPayment payment = new BankTransferPayment("1", paymentData);

        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }
}