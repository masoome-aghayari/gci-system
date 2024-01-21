package com.gci.agent.certificate.issuance.model.entity;

/*
 * @author masoome.aghayari
 * @since 12/28/23
 */

import com.gci.agent.certificate.issuance.model.enums.GoldType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Gold {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String code;

    private BigDecimal weight;

    @Enumerated(value = EnumType.STRING)
    private GoldType type;

    @ManyToOne
    private Workshop workshop;

    private Date produceDate;

    private boolean deleted;
}
