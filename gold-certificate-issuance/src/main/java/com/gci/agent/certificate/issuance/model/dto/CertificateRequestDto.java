package com.gci.agent.certificate.issuance.model.dto;

/*
 * @author masoome.aghayari
 * @since 12/28/23
 */

import com.gci.agent.certificate.issuance.model.enums.CertificateIssuanceStatus;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CertificateRequestDto {

    private final Date requestDate = new Date();

    @NonNull
    private GoldDto gold;

    private String trackingCode;
    private CertificateIssuanceStatus status;
    private UUID agentId;
}
