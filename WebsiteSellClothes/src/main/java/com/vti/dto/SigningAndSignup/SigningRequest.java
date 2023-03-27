package com.vti.dto.SigningAndSignup;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SigningRequest {

    @NotBlank(message = "{User.createUser.form.name.NotBlank}")
    @Length(min = 6, max = 50, message = "{User.createUser.form.Length6and50}")
    private String username;

    @NotEmpty(message = "{User.createUser.form.Empty}")
    @Length(min = 6, max = 50, message = "{User.createUser.form.Length6and50}")
    private String password;


}
