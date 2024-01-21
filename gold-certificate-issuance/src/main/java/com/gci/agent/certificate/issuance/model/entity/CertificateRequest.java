package com.gci.agent.certificate.issuance.model.entity;

/*
 * @author masoome.aghayari
 * @since 12/28/23
 */

import com.gci.agent.certificate.issuance.model.enums.CertificateIssuanceStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CertificateRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne(cascade = CascadeType.PERSIST)
    private Gold gold;

    private String trackingCode;
    private CertificateIssuanceStatus status;
    private Date requestDate;
}
