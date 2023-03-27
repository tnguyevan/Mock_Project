package com.vti.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class CartDTO {

    @NonNull
    private int productId;

    @NonNull
    private String productImageImage1;

    @NonNull
    private String productName;

    @NonNull
    private String productSize;

    @NonNull
    private int quantity;

    @NonNull
    private int productPrice;

    @NonNull
    private int productSalePrice;


}
