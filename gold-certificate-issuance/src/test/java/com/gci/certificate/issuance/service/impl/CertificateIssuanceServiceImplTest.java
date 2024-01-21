package com.gci.certificate.issuance.service.impl;

import com.gci.certificate.issuance.CertificateIssuanceTestsDataProvider;
import com.gci.certificate.issuance.exceptions.DuplicateGoldException;
import com.gci.certificate.issuance.exceptions.NoSuchWorkshopFoundException;
import com.gci.certificate.issuance.exceptions.RequestNotFoundException;
import com.gci.certificate.issuance.model.dto.CertificateRequestDto;
import com.gci.certificate.issuance.model.entity.CertificateRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;


/*
 * @author masoome.aghayari
 * @since 12/29/23
 */

@SpringBootTest
@AutoConfigureMockMvc
class CertificateIssuanceServiceImplTest extends CertificateIssuanceTestsDataProvider {

    @Test
    public void given_certificateRequestDto_when_goldCode_isNot_duplicate_and_workshop_found_then_returns_expectedResult()
            throws DuplicateGoldException, NoSuchWorkshopFoundException {
        var expectedResult = getCertificateRequestDto();
        var requestEntity = getCertificateRequest(expectedResult);
        requestEntity.setId(UUID.randomUUID());

        doReturn(requestEntity).when(certificateRequestRepository).save(any(CertificateRequest.class));
        doReturn(getWorkshop()).when(workshopRepository).findByIdAndAgent_Id(expectedResult.getGold().getWorkshopId(), expectedResult.getAgentId());
        doReturn(requestEntity).when(mapper).dtoToEntity(expectedResult);
        doReturn(expectedResult).when(mapper).entityToDto(requestEntity);
        doReturn(false).when(certificateRequestRepository).existsByGold_Code(any(String.class));

        var actualResult = service.registerCertificateIssuanceRequest(expectedResult);
        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void given_certificateRequestDto_when_noWorkshopFound_then_throws_NoSuchWorkshopFoundException() {
        CertificateRequestDto requestDto = getCertificateRequestDto();
        doReturn(Optional.empty()).when(workshopRepository).findByIdAndAgent_Id(requestDto.getGold().getWorkshopId(), requestDto.getAgentId());
        assertThrows(NoSuchWorkshopFoundException.class, () -> service.registerCertificateIssuanceRequest(requestDto));
    }

    @Test
    public void given_certificateRequestDto_when_goldCode_is_duplicate_then_throws_DuplicateGoldException() {
        CertificateRequestDto requestDto = getCertificateRequestDto();
        doReturn(true).when(certificateRequestRepository).existsByGold_Code(requestDto.getGold().getCode());
        assertThrows(DuplicateGoldException.class, () -> service.registerCertificateIssuanceRequest(requestDto));
    }

    @Test
    public void given_trackingCode_when_exists_then_returns_expectedResult() throws RequestNotFoundException {
        var expectedResult = getCertificateRequestDto();
        var certificateRequest = getCertificateRequest(expectedResult);
        doReturn(Optional.of(certificateRequest)).when(certificateRequestRepository).findByTrackingCode(expectedResult.getTrackingCode());
        doReturn(expectedResult).when(mapper).entityToDto(certificateRequest);

        var actualResult = service.getCertificateIssuanceRequestStatus(expectedResult.getTrackingCode());
        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void given_trackingCode_when_notExists_then_throws_RequestNotFoundException() {
        String trackingCode = getTrackingCode();
        doReturn(Optional.empty()).when(certificateRequestRepository).findByTrackingCode(trackingCode);
        assertThrows(RequestNotFoundException.class, () -> service.getCertificateIssuanceRequestStatus(trackingCode));
    }

}