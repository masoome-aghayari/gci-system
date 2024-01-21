package com.gci.workshop.agent.model.dto;

/*
 * @author masoome.aghayari
 * @since 12/29/23
 */

import com.gci.workshop.agent.model.annotation.Name;
import com.gci.workshop.agent.model.annotation.NationalCode;
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

    @Name
    private String name;

    @Name
    private String surname;

    @NationalCode
    private String nationalCode;
}
