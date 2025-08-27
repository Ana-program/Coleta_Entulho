package br.com.osasco.wastepickup.dto;

import lombok.*;

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
}
