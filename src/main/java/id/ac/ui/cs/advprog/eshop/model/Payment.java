package id.ac.ui.cs.advprog.eshop.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;

@Getter
@Setter
public class Payment {
    private String id;
    private String method;
    private String status;
    private Map<String, String> paymentData;

    public Payment(String id, String method, String status, Map<String, String> paymentData) {
        this.id = id;
        this.method = method;
        this.paymentData = paymentData;
        setStatus(status);
    }

    public void setStatus(String status) {
        if (!PaymentStatus.contains(status)) {
            throw new IllegalArgumentException();
        }
        this.status = status;
    }
}