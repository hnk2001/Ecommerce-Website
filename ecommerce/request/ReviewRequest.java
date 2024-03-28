package com.harshal.ecommerce.request;

import lombok.Data;

@Data
public class ReviewRequest {
    
    private Long producttId;
    private String review;

}
