package com.gci.agent.certificate.issuance.model.dto;

/*
 * @author masoome.aghayari
 * @since 12/29/23
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class PersonDto {
    private UUID id;
    private String name;
    private String surname;
    private String nationalCode;
}
