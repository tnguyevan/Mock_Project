package com.vti.dto.SigningAndSignup;

import com.vti.validation.user.EmailNotExists;
import com.vti.validation.user.UsernameNotExists;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignupRequest {


    @NotBlank(message = "{User.createUser.form.name.NotBlank}")
    @Length(min = 6, max = 50, message = "{User.createUser.form.Length6and50}")
    @UsernameNotExists
    private String username;

    @NotBlank(message = "The email mustn't be null value")
    @Email(message = "{User.createUser.form.email.Email}")
    @Length(min = 6, max = 50, message = "{User.createUser.form.Length6and50}")
    @EmailNotExists
    private String email;

    @NotEmpty(message = "{User.createUser.form.Empty}")
    @Length(min = 6, max = 50, message = "{User.createUser.form.Length6and50}")
    private String password;

    @NotEmpty(message = "{User.createUser.form.Empty}")
    @Length(max = 50, message = "{User.createUser.form.Length}")
    private String firstName;

    @NotEmpty(message = "{User.createUser.form.Empty}")
    @Length(max = 50, message = "{User.createUser.form.Length}")
    private String lastName;

    private List<String> role;


}
