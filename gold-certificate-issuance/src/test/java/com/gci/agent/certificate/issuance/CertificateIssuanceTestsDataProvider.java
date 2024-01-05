package com.gci.agent.certificate.issuance;

/*
 * @author masoome.aghayari
 * @since 12/29/23
 */

import com.gci.agent.certificate.issuance.mapper.CertificateRequestMapper;
import com.gci.agent.certificate.issuance.mapper.WorkshopMapper;
import com.gci.agent.certificate.issuance.model.dto.CertificateRequestDto;
import com.gci.agent.certificate.issuance.model.dto.GoldDto;
import com.gci.agent.certificate.issuance.model.dto.WorkshopAgentDto;
import com.gci.agent.certificate.issuance.model.dto.WorkshopDto;
import com.gci.agent.certificate.issuance.model.entity.CertificateRequest;
import com.gci.agent.certificate.issuance.model.entity.Workshop;
import com.gci.agent.certificate.issuance.model.enums.CertificateIssuanceStatus;
import com.gci.agent.certificate.issuance.model.enums.GoldType;
import com.gci.agent.certificate.issuance.repository.CertificateRequestRepository;
import com.gci.agent.certificate.issuance.repository.WorkshopRepository;
import com.gci.agent.certificate.issuance.service.impl.CertificateIssuanceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

@SpringBootTest
@AutoConfigureMockMvc
public class CertificateIssuanceTestsDataProvider {
    @Autowired
    protected MockMvc mockMvc;

    @MockBean
    protected CertificateRequestRepository certificateRequestRepository;

    @MockBean
    protected WorkshopRepository workshopRepository;

    @Autowired
    protected CertificateIssuanceServiceImpl service;

    @MockBean
    protected CertificateRequestMapper mapper;

    @MockBean
    protected WorkshopMapper workshopMapper;

    String workshopId = getRandomId();
    String agentId = getRandomId();

    String goldId = getRandomId();

    protected CertificateRequestDto getCertificateRequestDto() {
        return CertificateRequestDto.builder()
                .trackingCode(getEightDigitsRandomString())
                .gold(getGoldDto())
                .status(CertificateIssuanceStatus.AWAITING_ADMIN_APPROVAL)
                .build();
    }

    private GoldDto getGoldDto() {
        return GoldDto.builder().id(goldId)
                .produceDate(new Date())
                .type(GoldType.JEWELERY)
                .weight(BigDecimal.valueOf(2.34))
                .workshopId(workshopId)
                .code(getEightDigitsRandomString())
                .build();
    }

    private String getEightDigitsRandomString() {
        return String.valueOf(new Random().nextInt(10_000_000, 100_000_000));
    }

    private WorkshopDto getWorkshopDto() {
        return WorkshopDto.builder()
                .id(workshopId)
                .workshopCode(getEightDigitsRandomString())
                .licenseNumber(getEightDigitsRandomString())
                .agentId(agentId)
                .build();
    }

    private WorkshopAgentDto getWorkshopAgent() {
        return WorkshopAgentDto.builder()
                .id(agentId)
                .name("John")
                .surname("Doe")
                .nationalCode("4310928838")
                .username("John_Doe")
                .build();
    }

    private String getRandomId() {
        return UUID.randomUUID().toString();
    }

    protected CertificateRequest getCertificateRequest(CertificateRequestDto certificateRequestDto) {
        return mapper.dtoToEntity(certificateRequestDto);
    }

    protected Optional<Workshop> getWorkshop() {
        var workshop = workshopMapper.dtoToEntity(getWorkshopDto());
        workshop.setDeleted(false);
        return Optional.of(workshop);
    }

    protected String getTrackingCode() {
        return getEightDigitsRandomString();
    }
}
