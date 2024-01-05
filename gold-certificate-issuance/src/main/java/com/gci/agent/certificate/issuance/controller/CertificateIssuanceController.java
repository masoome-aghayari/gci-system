package com.gci.agent.certificate.issuance.controller;

/*
 * @author masoome.aghayari
 * @since 12/28/23
 */

import com.gci.agent.certificate.issuance.exceptions.DuplicateGoldException;
import com.gci.agent.certificate.issuance.exceptions.NoSuchWorkshopFoundException;
import com.gci.agent.certificate.issuance.exceptions.RequestNotFoundException;
import com.gci.agent.certificate.issuance.model.annotation.Digital;
import com.gci.agent.certificate.issuance.model.dto.CertificateRequestDto;
import com.gci.agent.certificate.issuance.service.CertificateIssuanceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(CertificateIssuanceController.BASE_PATH)
@PreAuthorize("hasRole('WORKSHOP_AGENT')")
@RequiredArgsConstructor
public class CertificateIssuanceController {
    public static final String BASE_PATH = "/certificate/issue";
    private static final String ISSUE_STATUS = "/{trackingCode}";
    private final CertificateIssuanceService service;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public CertificateRequestDto issue(@Valid @RequestBody CertificateRequestDto request)
            throws DuplicateGoldException, NoSuchWorkshopFoundException {
        return service.registerCertificateIssuanceRequest(request);
    }

    @GetMapping(value = ISSUE_STATUS, produces = MediaType.APPLICATION_JSON_VALUE)
    public CertificateRequestDto issuanceStatus(@Digital(message = "trackingCode must be a fully 8 digits string")
                                                @PathVariable String trackingCode) throws RequestNotFoundException {
        return service.getCertificateIssuanceRequestStatus(trackingCode);
    }
}
