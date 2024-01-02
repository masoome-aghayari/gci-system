package com.gci.agent.certificate.issuance.service.impl;

import com.gci.agent.certificate.issuance.CertificateIssuanceTestsDataProvider;
import com.gci.agent.certificate.issuance.exceptions.DuplicateGoldException;
import com.gci.agent.certificate.issuance.exceptions.InvalidTrackingCodeException;
import com.gci.agent.certificate.issuance.exceptions.RequestNotFoundException;
import com.gci.agent.certificate.issuance.model.dto.CertificateRequestDto;
import com.gci.agent.certificate.issuance.model.entity.CertificateRequest;
import net.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


/*
 * @author masoome.aghayari
 * @since 12/29/23
 */

@SpringBootTest
@AutoConfigureMockMvc
class CertificateIssuanceServiceImplTest extends CertificateIssuanceTestsDataProvider {

    @Test
    public void given_certificateRequestDto_when_goldCode_isNot_duplicate_then_returns_expectedResult() throws DuplicateGoldException {
        CertificateRequestDto expectedResult = getCertificateRequestDto();
        CertificateRequest requestEntity = getCertificateRequest();

        when(repository.existsByGold_Code(expectedResult.getGold().getCode())).thenReturn(false);
        when(mapper.dtoToEntity(expectedResult)).thenReturn(requestEntity);
        when(repository.save(any())).thenReturn(getFilledRequestEntityById(requestEntity));
        when(mapper.entityToDto(requestEntity)).thenReturn(expectedResult);

        CertificateRequestDto actualResult = service.registerCertificateIssuanceRequest(expectedResult);
        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void given_certificateRequestDto_when_goldCode_is_duplicate_then_throws_DuplicateGoldException() {
        CertificateRequestDto requestDto = getCertificateRequestDto();
        when(repository.existsByGold_Code(requestDto.getGold().getCode())).thenReturn(true);
        assertThrows(DuplicateGoldException.class,
                () -> service.registerCertificateIssuanceRequest(requestDto));
    }

    private CertificateRequest getFilledRequestEntityById(CertificateRequest requestEntity) {
        requestEntity.setId(UUID.randomUUID().toString());
        return requestEntity;
    }


    @Test
    public void given_trackingCode_when_exists_then_returns_expectedResult()
            throws RequestNotFoundException, InvalidTrackingCodeException {
        var expectedResult = getCertificateRequestDto();
        CertificateRequestDto actualResult = service.getCertificateIssuanceRequestStatus(expectedResult.getTrackingCode());
        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void given_trackingCode_when_notExists_then_throws_RequestNotFoundException() {
        String trackingCode = getTrackingCode();
        when(repository.findByTrackingCode(trackingCode)).thenReturn(Optional.empty());
        assertThrows(RequestNotFoundException.class, () -> service.getCertificateIssuanceRequestStatus(trackingCode));
    }

    @Test
    public void given_trackingCode_when_isNot_digital_then_throws_InvalidTrackingCodeException() {
        String trackingCode = RandomString.make(10);
        assertThrows(InvalidTrackingCodeException.class, () -> service.getCertificateIssuanceRequestStatus(trackingCode));
    }

}