package com.gci.agent.certificate.issuance.service;

import com.gci.agent.certificate.issuance.exceptions.DuplicateGoldException;
import com.gci.agent.certificate.issuance.exceptions.InvalidTrackingCodeException;
import com.gci.agent.certificate.issuance.exceptions.RequestNotFoundException;
import com.gci.agent.certificate.issuance.model.dto.CertificateRequestDto;
import org.springframework.stereotype.Service;

/*
 * @author masoome.aghayari
 * @since 12/28/23
 */
@Service
public interface CertificateIssuanceService {

    CertificateRequestDto registerCertificateIssuanceRequest(CertificateRequestDto request) throws DuplicateGoldException;

    CertificateRequestDto getCertificateIssuanceRequestStatus(String trackingCode) throws RequestNotFoundException, InvalidTrackingCodeException;
}
