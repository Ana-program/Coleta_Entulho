package br.com.osasco.wastepickup.dto;

import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class AdminDTO {

    private String name;
    private String address;
    private String phone;
    private String email;
    private LocalDate birthDate;
    private String gender;
    private String zipCode;
    private String street;
    private String number;
    private String complement;
    private String neighborhood;
    private String city;
    private String state;
    private String mobile;
}

