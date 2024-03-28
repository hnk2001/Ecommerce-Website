package com.harshal.ecommerce.request;

import lombok.Data;

@Data
public class RatingRequest {
    
    private Long producttId;
    private double rating;

}
