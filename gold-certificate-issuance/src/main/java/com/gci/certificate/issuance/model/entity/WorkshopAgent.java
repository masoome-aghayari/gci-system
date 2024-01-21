package com.gci.certificate.issuance.model.entity;

/*
 * @author masoome.aghayari
 * @since 12/28/23
 */

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class WorkshopAgent extends Person {
    private String username;
    private String password;
}
