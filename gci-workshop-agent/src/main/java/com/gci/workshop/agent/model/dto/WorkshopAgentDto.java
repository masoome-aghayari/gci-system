package com.gci.workshop.agent.model.dto;

/*
 * @author masoome.aghayari
 * @since 12/29/23
 */

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
    private String username;
    private String password;
}
