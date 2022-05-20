package kz.iitu.itse1909r.mukhitdinov.core.controller.models.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateRequestModel {
    @NotEmpty(message = "Username cannot be empty")
    @NotNull(message = "Username cannot be null")
    private String username;

    @NotNull(message = "Password cannot be null")
    @Length(min = 8, message = "Password must contain at least 8 characters")
    private String password;

    private String firstName;
    private String lastName;
}
