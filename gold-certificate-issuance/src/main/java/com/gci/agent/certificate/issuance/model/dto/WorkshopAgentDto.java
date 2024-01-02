package com.gci.agent.certificate.issuance.model.dto;

/*
 * @author masoome.aghayari
 * @since 12/29/23
 */

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class WorkshopAgentDto extends PersonDto {

    @NotBlank
    private String username;

    @NotBlank
    private String password;
}
