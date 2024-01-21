package com.gci.certificate.issuance.model.entity;

/*
 * @author masoome.aghayari
 * @since 12/28/23
 */

import com.gci.certificate.issuance.model.annotation.Name;
import com.gci.certificate.issuance.model.annotation.NationalCode;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Data
@NoArgsConstructor
@MappedSuperclass
@AllArgsConstructor
@SuperBuilder
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Name
    private String name;

    @Name
    private String surname;

    @NationalCode
    private String nationalCode;

    @Column
    private boolean deleted;

}
