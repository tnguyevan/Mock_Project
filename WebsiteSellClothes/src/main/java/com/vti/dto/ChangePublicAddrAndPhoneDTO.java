package com.vti.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class ChangePublicAddrAndPhoneDTO {


    @NonNull
    @NotEmpty(message = "{User.createUser.form.Empty}")
    private String address;

    @NonNull
    @NotEmpty(message = "{User.createUser.form.Empty}")
    private String phoneNumber;


}
