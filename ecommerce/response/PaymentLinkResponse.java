package com.harshal.ecommerce.response;

import lombok.Data;

@Data
public class PaymentLinkResponse {
    
    private String payment_link_url;
    private String payment_link_id;
    
    public PaymentLinkResponse() {
    }
    public PaymentLinkResponse(String payment_link_url, String payment_link_id) {
        this.payment_link_url = payment_link_url;
        this.payment_link_id = payment_link_id;
    }

    

}
