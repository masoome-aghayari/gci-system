package com.gci.agent.certificate.issuance.service.impl;

/*
 * @author masoome.aghayari
 * @since 12/28/23
 */

import com.gci.agent.certificate.issuance.exceptions.DuplicateGoldException;
import com.gci.agent.certificate.issuance.exceptions.InvalidTrackingCodeException;
import com.gci.agent.certificate.issuance.exceptions.RequestNotFoundException;
import com.gci.agent.certificate.issuance.mapper.CertificateRequestMapper;
import com.gci.agent.certificate.issuance.model.dto.CertificateRequestDto;
import com.gci.agent.certificate.issuance.repository.CertificateRequestRepository;
import com.gci.agent.certificate.issuance.service.CertificateIssuanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CertificateIssuanceServiceImpl implements CertificateIssuanceService {
    private final CertificateRequestRepository repository;
    private final CertificateRequestMapper mapper;

    @Override
    public CertificateRequestDto registerCertificateIssuanceRequest(CertificateRequestDto requestDto)
            throws DuplicateGoldException {
        var goldCode = requestDto.getGold().getCode();
        checkGoldCodeDuplication(goldCode);
        return doRegister(requestDto);
    }

    @Override
    public CertificateRequestDto getCertificateIssuanceRequestStatus(String trackingCode)
            throws RequestNotFoundException {
        var result = repository.findByTrackingCode(trackingCode);
        var certificateRequest = result.orElseThrow(() ->
                new RequestNotFoundException("No request with trackingCode: " + trackingCode + " found"));
        return mapper.entityToDto(certificateRequest);
    }

    private CertificateRequestDto doRegister(CertificateRequestDto requestDto) {
        var requestEntity = mapper.dtoToEntity(requestDto);
        requestEntity = repository.save(requestEntity);
        return mapper.entityToDto(requestEntity);
    }

    private void checkGoldCodeDuplication(String goldCode) throws DuplicateGoldException {
        try {
            assert repository.existsByGold_Code(goldCode) : "Another gold piece with code: " + goldCode + " exists";
        } catch (AssertionError e) {
            throw new DuplicateGoldException(e.getMessage());
        }
    }

}
