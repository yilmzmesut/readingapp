package org.yilmzmesut.readingapp.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequest {
    @NotNull(message = "name cannot be null")
    @NotEmpty(message = "name cannot be empty")
    private String name;
    @NotNull(message = "surname cannot be null")
    @NotEmpty(message = "surname cannot be empty")
    private String surname;
    @NotNull(message = "email cannot be null")
    @NotEmpty(message = "email cannot be empty")
    @Email
    private String email;
    @NotNull(message = "password cannot be null")
    @NotEmpty(message = "password cannot be empty")
    private String password;
}
