package com.gci.agent.certificate.issuance.model.dto;

/*
 * @author masoome.aghayari
 * @since 12/28/23
 */

import com.gci.agent.certificate.issuance.model.enums.CertificateIssuanceStatus;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CertificateRequestDto {

    private final Date requestDate = new Date();
    private String trackingCode;
    @NonNull
    private GoldDto gold;

    @NotNull
    private WorkshopAgentDto requester;

    @NotNull
    private CertificateIssuanceStatus status;
}