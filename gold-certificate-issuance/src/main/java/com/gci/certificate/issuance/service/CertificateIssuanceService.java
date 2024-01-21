package com.gci.certificate.issuance.service;

import com.gci.certificate.issuance.exceptions.DuplicateGoldException;
import com.gci.certificate.issuance.exceptions.NoSuchWorkshopFoundException;
import com.gci.certificate.issuance.exceptions.RequestNotFoundException;
import com.gci.certificate.issuance.model.dto.CertificateRequestDto;
import org.springframework.stereotype.Service;

/*
 * @author masoome.aghayari
 * @since 12/28/23
 */
@Service
public interface CertificateIssuanceService {

    CertificateRequestDto registerCertificateIssuanceRequest(CertificateRequestDto request) throws DuplicateGoldException, NoSuchWorkshopFoundException;

    CertificateRequestDto getCertificateIssuanceRequestStatus(String trackingCode) throws RequestNotFoundException;
}
