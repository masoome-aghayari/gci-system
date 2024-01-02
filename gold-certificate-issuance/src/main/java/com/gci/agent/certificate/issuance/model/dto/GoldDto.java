package com.gci.agent.certificate.issuance.model.dto;

/*
 * @author masoome.aghayari
 * @since 12/28/23
 */

import com.gci.agent.certificate.issuance.model.enums.GoldType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GoldDto {

    private String id;

    @NotBlank
    private String code;

    @NotNull
    private BigDecimal weight;

    @NotNull
    private GoldType type;

    @NotNull
    private WorkshopDto workShop;

    @NotNull
    private Date produceDate;
}
