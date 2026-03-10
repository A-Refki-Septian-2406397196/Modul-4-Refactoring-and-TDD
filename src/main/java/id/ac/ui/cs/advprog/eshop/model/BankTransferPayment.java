package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;

import java.util.Map;

public class BankTransferPayment extends Payment {

    public BankTransferPayment(String id, Map<String, String> paymentData) {
        super(id, "Bank Transfer", determineStatus(paymentData), paymentData);
    }

    private static String determineStatus(Map<String, String> paymentData) {
        String bankName = paymentData.get("bankName");
        String referenceCode = paymentData.get("referenceCode");

        if (isNullOrEmpty(bankName) || isNullOrEmpty(referenceCode)) {
            return PaymentStatus.REJECTED.getValue();
        }

        return PaymentStatus.SUCCESS.getValue();
    }

    private static boolean isNullOrEmpty(String value) {
        return value == null || value.isEmpty();
    }
}