package com.gci.certificate.issuance.service.impl;

/*
 * @author masoome.aghayari
 * @since 12/28/23
 */

import com.gci.certificate.issuance.exceptions.DuplicateGoldException;
import com.gci.certificate.issuance.exceptions.NoSuchWorkshopFoundException;
import com.gci.certificate.issuance.exceptions.RequestNotFoundException;
import com.gci.certificate.issuance.mapper.CertificateRequestMapper;
import com.gci.certificate.issuance.model.dto.CertificateRequestDto;
import com.gci.certificate.issuance.model.entity.Workshop;
import com.gci.certificate.issuance.model.enums.CertificateIssuanceStatus;
import com.gci.certificate.issuance.repository.CertificateRequestRepository;
import com.gci.certificate.issuance.repository.WorkshopRepository;
import com.gci.certificate.issuance.service.CertificateIssuanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CertificateIssuanceServiceImpl implements CertificateIssuanceService {
    private final CertificateRequestRepository repository;
    private final WorkshopRepository workshopRepository;
    private final CertificateRequestMapper mapper;

    @Override
    public CertificateRequestDto registerCertificateIssuanceRequest(CertificateRequestDto requestDto)
            throws DuplicateGoldException, NoSuchWorkshopFoundException {
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

    private CertificateRequestDto doRegister(CertificateRequestDto requestDto) throws NoSuchWorkshopFoundException {
        Workshop workshop = getWorkshop(requestDto.getGold().getWorkshopId(), requestDto.getAgentId());
        var requestEntity = mapper.dtoToEntity(requestDto);
        requestEntity.getGold().setWorkshop(workshop);
        var trackingCode = UUID.randomUUID().toString();
        requestEntity.setStatus(CertificateIssuanceStatus.AWAITING_ADMIN_APPROVAL);
        requestEntity.setTrackingCode(trackingCode);
        repository.save(requestEntity);
        requestDto.setTrackingCode(trackingCode);
        return requestDto;
    }

    private Workshop getWorkshop(UUID workshopId, UUID agentId) throws NoSuchWorkshopFoundException {
        var workshop = workshopRepository.findByIdAndAgent_Id(workshopId, agentId);
        return workshop.orElseThrow(() -> new NoSuchWorkshopFoundException("workshop id or agentId is not correct"));
    }

    private void checkGoldCodeDuplication(String goldCode) throws DuplicateGoldException {
        if (repository.existsByGold_Code(goldCode))
            throw new DuplicateGoldException("Another gold piece with code: " + goldCode + " exists");
    }

}
