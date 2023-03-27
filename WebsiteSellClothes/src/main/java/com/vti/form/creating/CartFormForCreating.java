package com.vti.form.creating;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@NoArgsConstructor
public class CartFormForCreating {


    private Integer userId;

    @NotNull(message = "{User.createUser.form.Empty}")
    @Positive(message = "The ProductId must be greater than or equal 1")
    private Integer productId;


}
