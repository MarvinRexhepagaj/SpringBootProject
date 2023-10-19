package com.lhind.internship.FinalProject.model.dto;

import com.lhind.internship.FinalProject.model.enums.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class PasswordDto {
    private Long id;

    @NotBlank(message = "First name cannot be blank")
    private String firstName;

    private String middleName;

    @NotBlank(message = "Last name cannot be blank")
    private String lastName;


    @Email(message = "Invalid email format")
    private String email;


    private String phoneNumber;

    @NotBlank(message = "Address cannot be blank")
    private String address;

    @NotBlank(message = "Password cannot be blank")

    private String password;

    private UserRole role;
}
