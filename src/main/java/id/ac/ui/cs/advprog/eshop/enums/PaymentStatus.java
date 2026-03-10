package id.ac.ui.cs.advprog.eshop.enums;

public enum PaymentStatus {
    SUCCESS,
    REJECTED;

    public static boolean contains(String value) {
        for (PaymentStatus status : PaymentStatus.values()) {
            if (status.name().equals(value)) {
                return true;
            }
        }
        return false;
    }
}