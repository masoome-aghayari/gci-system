package com.gci.certificate.issuance.model.dto;

/*
 * @author masoome.aghayari
 * @since 12/28/23
 */

import com.gci.certificate.issuance.model.annotation.Digital;
import com.gci.certificate.issuance.model.enums.GoldType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GoldDto {

    private UUID id;

    @NotBlank
    @Digital(message = "gold code must be a fully 8 digits string")
    private String code;

    @NotNull
    private BigDecimal weight;

    @NotNull
    private GoldType type;

    @NotBlank
    private UUID workshopId;

    @NotNull
    private Date produceDate;
}
