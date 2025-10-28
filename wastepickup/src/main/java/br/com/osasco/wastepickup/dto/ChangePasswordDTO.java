package br.com.osasco.wastepickup.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ChangePasswordDTO {

    @NotBlank
    private String newPassword;

    @NotBlank
    private String cpf;

}
