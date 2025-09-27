package br.com.osasco.wastepickup.dto;

import br.com.osasco.wastepickup.role.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record RegisterDTO(
        @NotBlank(message = "Login is required")
        String login,

        @NotBlank(message = "Password is required")
        String password,

        Role role,

        @NotBlank(message = "Name is required")
        String name,

        @NotBlank(message = "CPF is required")
        String cpf,

        String rg,

        @NotNull(message = "Birth date is required")
        LocalDate birthDate,

        @NotBlank(message = "Gender is required")
        String gender,

        @NotBlank(message = "Zip code is required")
        String zipCode,

        @NotBlank(message = "Street is required")
        String street,

        @NotBlank(message = "Number is required")
        String number,

        String complement,

        @NotBlank(message = "Neighborhood is required")
        String neighborhood,

        @NotBlank(message = "City is required")
        String city,

        @NotBlank(message = "State is required")
        String state,

        String phone,

        @NotBlank(message = "Mobile is required")
        String mobile,

        @NotBlank(message = "Email is required")
        @Email(message = "Email should be valid")
        String email
        ) {
}



