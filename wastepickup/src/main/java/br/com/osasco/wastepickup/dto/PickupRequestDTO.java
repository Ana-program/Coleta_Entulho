package br.com.osasco.wastepickup.dto;

import br.com.osasco.wastepickup.model.RequestStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class PickupRequestDTO {

    private String requesterName;
    private String debrisType;
    private double estimatedQuantity;
    private LocalDate requestDate;
    private String address;

    @Enumerated(EnumType.STRING)
    private RequestStatus status = RequestStatus.PENDING;
}
