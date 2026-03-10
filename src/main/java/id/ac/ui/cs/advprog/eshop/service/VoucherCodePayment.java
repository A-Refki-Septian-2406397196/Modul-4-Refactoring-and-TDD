package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;

import java.util.Map;

public class VoucherCodePayment extends Payment {

    private static final String METHOD_NAME = "Voucher Code";
    private static final String VOUCHER_CODE_KEY = "voucherCode";
    private static final String REQUIRED_PREFIX = "ESHOP";
    private static final int REQUIRED_LENGTH = 16;
    private static final int REQUIRED_DIGIT_COUNT = 8;

    public VoucherCodePayment(String id, Map<String, String> paymentData) {
        super(id, METHOD_NAME, determineStatus(paymentData), paymentData);
    }

    private static String determineStatus(Map<String, String> paymentData) {
        String voucherCode = paymentData.get(VOUCHER_CODE_KEY);
        return isValidVoucherCode(voucherCode)
                ? PaymentStatus.SUCCESS.getValue()
                : PaymentStatus.REJECTED.getValue();
    }

    private static boolean isValidVoucherCode(String voucherCode) {
        return voucherCode != null
                && hasValidLength(voucherCode)
                && hasValidPrefix(voucherCode)
                && hasRequiredDigitCount(voucherCode);
    }

    private static boolean hasValidLength(String voucherCode) {
        return voucherCode.length() == REQUIRED_LENGTH;
    }

    private static boolean hasValidPrefix(String voucherCode) {
        return voucherCode.startsWith(REQUIRED_PREFIX);
    }

    private static boolean hasRequiredDigitCount(String voucherCode) {
        return countDigits(voucherCode) == REQUIRED_DIGIT_COUNT;
    }

    private static int countDigits(String voucherCode) {
        int digitCount = 0;

        for (char character : voucherCode.toCharArray()) {
            if (Character.isDigit(character)) {
                digitCount++;
            }
        }

        return digitCount;
    }
}