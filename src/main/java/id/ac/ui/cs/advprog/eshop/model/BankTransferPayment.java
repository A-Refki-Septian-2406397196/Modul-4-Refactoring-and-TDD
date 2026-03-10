package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;

import java.util.Map;

public class BankTransferPayment extends Payment {

    private static final String METHOD_NAME = "Bank Transfer";
    private static final String BANK_NAME_KEY = "bankName";
    private static final String REFERENCE_CODE_KEY = "referenceCode";

    public BankTransferPayment(String id, Map<String, String> paymentData) {
        super(id, METHOD_NAME, determineStatus(paymentData), paymentData);
    }

    private static String determineStatus(Map<String, String> paymentData) {
        String bankName = paymentData.get(BANK_NAME_KEY);
        String referenceCode = paymentData.get(REFERENCE_CODE_KEY);

        return hasCompleteData(bankName, referenceCode)
                ? PaymentStatus.SUCCESS.getValue()
                : PaymentStatus.REJECTED.getValue();
    }

    private static boolean hasCompleteData(String bankName, String referenceCode) {
        return !isNullOrEmpty(bankName) && !isNullOrEmpty(referenceCode);
    }

    private static boolean isNullOrEmpty(String value) {
        return value == null || value.isEmpty();
    }
}