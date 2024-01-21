package com.gci.workshop.agent.model.entity;

/*
 * @author masoome.aghayari
 * @since 12/28/23
 */

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
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

    private String name;
    private String surname;
    private String nationalCode;
    private boolean deleted;
}
