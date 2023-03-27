package com.vti.form.updating;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@NoArgsConstructor
public class CartFormForUpdating {

    @NotNull(message = "{User.createUser.form.Empty}")
    @Positive(message = "The Quantity must be greater than or equal 1")
    private Integer quantity;

}
