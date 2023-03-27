package com.vti.form.creating;

import com.vti.entity.OderList;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@NoArgsConstructor
public class OderListFormForCreating {

    @NotNull(message = "{User.createUser.form.Empty}")
    @Positive(message = "The UserId must be greater than or equal 1")
    private Integer userId;

    private Integer totalPayment;

    private OderList.Status status;


}
