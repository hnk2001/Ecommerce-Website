package com.harshal.ecommerce.model;

import java.time.LocalDate;

import lombok.Data;

@Data
public class PaymentInformation {

    private String cardholderName;

    private String cardNumber;

    private LocalDate expirationDate;

    private String cvv;

    
}
