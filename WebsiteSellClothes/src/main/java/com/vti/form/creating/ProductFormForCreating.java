package com.vti.form.creating;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Positive;

@Data
@NoArgsConstructor
public class ProductFormForCreating {

    private String name;

    @Positive(message = "The catalogId must be greater than or equal 1")
    private Integer catalogId;

    private String describe;

    private String size;

    private short amount;

    private int purchasePrice;

    private int price;

    private int salePrice;

    private String review;

    private ImageDTO image;

    @Data
    @NoArgsConstructor
    public static class ImageDTO {

        private String image1;

        private String image2;

        private String image3;

        private String image4;

        private String image5;

        private String image6;

    }
}
