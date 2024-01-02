package com.gci.agent.certificate.issuance.model.dto;

/*
 * @author masoome.aghayari
 * @since 12/28/23
 */

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WorkshopDto {

    private String id;

    @NotBlank
    private String workshopCode;

    @NotBlank
    private String licenseNumber;

    @NotNull
    private WorkshopAgentDto agent;

}
