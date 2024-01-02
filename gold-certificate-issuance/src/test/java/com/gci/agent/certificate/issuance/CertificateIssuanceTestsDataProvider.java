package com.gci.agent.certificate.issuance;

/*
 * @author masoome.aghayari
 * @since 12/29/23
 */

import com.gci.agent.certificate.issuance.mapper.CertificateRequestMapper;
import com.gci.agent.certificate.issuance.model.dto.CertificateRequestDto;
import com.gci.agent.certificate.issuance.model.dto.GoldDto;
import com.gci.agent.certificate.issuance.model.dto.WorkshopAgentDto;
import com.gci.agent.certificate.issuance.model.dto.WorkshopDto;
import com.gci.agent.certificate.issuance.model.entity.CertificateRequest;
import com.gci.agent.certificate.issuance.model.enums.CertificateIssuanceStatus;
import com.gci.agent.certificate.issuance.model.enums.GoldType;
import com.gci.agent.certificate.issuance.repository.CertificateRequestRepository;
import com.gci.agent.certificate.issuance.service.impl.CertificateIssuanceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

@SpringBootTest
@AutoConfigureMockMvc
public class CertificateIssuanceTestsDataProvider {
    @Autowired
    protected MockMvc mockMvc;

    @MockBean
    protected CertificateRequestRepository repository;

    @Autowired
    protected CertificateIssuanceServiceImpl service;

    @MockBean
    protected CertificateRequestMapper mapper;

    protected CertificateRequestDto getCertificateRequestDto() {
        return CertificateRequestDto.builder()
                .requester(getWorkshopAgent())
                .trackingCode(getStringifySevenDigitsRandomNumber())
                .gold(getGoldDto())
                .status(CertificateIssuanceStatus.AWAITING_ADMIN_APPROVAL)
                .build();
    }

    private GoldDto getGoldDto() {
        return GoldDto.builder().id(getRandomId())
                .produceDate(new Date())
                .type(GoldType.JEWELERY)
                .weight(BigDecimal.valueOf(2.34))
                .workShop(getWorkshopDto())
                .code(getStringifySevenDigitsRandomNumber())
                .build();
    }

    private String getStringifySevenDigitsRandomNumber() {
        return String.valueOf(new Random().nextInt(1_000_000, 10_000_000));
    }

    private WorkshopDto getWorkshopDto() {
        return WorkshopDto.builder()
                .id(getRandomId())
                .workshopCode(getStringifySevenDigitsRandomNumber())
                .licenseNumber(getStringifySevenDigitsRandomNumber())
                .agent(getWorkshopAgent())
                .build();
    }

    private WorkshopAgentDto getWorkshopAgent() {
        return WorkshopAgentDto.builder()
                .id(getRandomId())
                .name("John")
                .surname("Doe")
                .nationalCode("4310928838")
                .username("John_Doe")
                .build();
    }

    private String getRandomId() {
        return UUID.randomUUID().toString();
    }

    protected CertificateRequest getCertificateRequest() {
        return mapper.dtoToEntity(getCertificateRequestDto());
    }

    protected String getTrackingCode() {
        return getStringifySevenDigitsRandomNumber();
    }
}
