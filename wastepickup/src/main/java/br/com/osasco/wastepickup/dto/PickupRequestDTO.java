package br.com.osasco.wastepickup.dto;

import java.time.LocalDate;

public class PickupRequestDTO {

    private String requesterName;
    private String address;
    private String debrisType;
    private double estimatedQuantity;
    private LocalDate requestDate;
}
