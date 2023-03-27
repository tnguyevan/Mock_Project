package com.vti.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class ChangePublicProfileDTO {


    @NonNull
    @Length(max = 50, message = "{User.createUser.form.Length}")
    @NotEmpty(message = "{User.createUser.form.Empty}")
    private String firstName;

    @NonNull
    @Length(max = 50, message = "{User.createUser.form.Length}")
    @NotEmpty(message = "{User.createUser.form.Empty}")
    private String lastName;

    @NonNull
    @NotEmpty(message = "{User.createUser.form.Empty}")
    private String address;

    @NonNull
    @NotEmpty(message = "{User.createUser.form.Empty}")
    private String phoneNumber;


}
