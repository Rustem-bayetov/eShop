package edu.mum.eshop.domain.review;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class ReviewCreateModel {
    public Integer productId;

    public String body;
}
