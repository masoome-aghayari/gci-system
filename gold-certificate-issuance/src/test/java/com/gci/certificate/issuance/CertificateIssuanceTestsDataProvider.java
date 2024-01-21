package com.gci.certificate.issuance;

/*
 * @author masoome.aghayari
 * @since 12/29/23
 */

import com.gci.certificate.issuance.mapper.CertificateRequestMapper;
import com.gci.certificate.issuance.mapper.WorkshopMapper;
import com.gci.certificate.issuance.model.dto.CertificateRequestDto;
import com.gci.certificate.issuance.model.dto.GoldDto;
import com.gci.certificate.issuance.model.dto.WorkshopAgentDto;
import com.gci.certificate.issuance.model.dto.WorkshopDto;
import com.gci.certificate.issuance.model.entity.CertificateRequest;
import com.gci.certificate.issuance.model.entity.Gold;
import com.gci.certificate.issuance.model.entity.Workshop;
import com.gci.certificate.issuance.model.entity.WorkshopAgent;
import com.gci.certificate.issuance.model.enums.CertificateIssuanceStatus;
import com.gci.certificate.issuance.model.enums.GoldType;
import com.gci.certificate.issuance.repository.CertificateRequestRepository;
import com.gci.certificate.issuance.repository.WorkshopRepository;
import com.gci.certificate.issuance.service.impl.CertificateIssuanceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

@SpringBootTest
public class CertificateIssuanceTestsDataProvider {

    @Autowired
    protected CertificateIssuanceServiceImpl service;

    @MockBean
    protected CertificateRequestRepository certificateRequestRepository;

    @MockBean
    protected WorkshopRepository workshopRepository;

    @MockBean
    protected CertificateRequestMapper mapper;

    @MockBean
    protected WorkshopMapper workshopMapper;

    UUID workshopId = getRandomId();

    UUID agentId = getRandomId();

    UUID goldId = getRandomId();

    protected CertificateRequestDto getCertificateRequestDto() {
        return CertificateRequestDto.builder()
                .trackingCode(getEightDigitsRandomString())
                .gold(getGoldDto())
                .status(CertificateIssuanceStatus.AWAITING_ADMIN_APPROVAL)
                .agentId(agentId)
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

    private WorkshopAgentDto getWorkshopAgentDto() {
        return WorkshopAgentDto.builder()
                .id(agentId)
                .name("John")
                .surname("Doe")
                .nationalCode("4310928838")
                .username("John_Doe")
                .build();
    }

    private UUID getRandomId() {
        return UUID.randomUUID();
    }

    protected CertificateRequest getCertificateRequest(CertificateRequestDto certificateRequestDto) {
        return CertificateRequest.builder()
                .requestDate(certificateRequestDto.getRequestDate())
                .gold(getGold(getGoldDto()))
                .status(certificateRequestDto.getStatus())
                .trackingCode(certificateRequestDto.getTrackingCode())
                .build();
    }

    private Gold getGold(GoldDto goldDto) {
        return Gold.builder()
                .code(goldDto.getCode())
                .id(goldDto.getId())
                .produceDate(goldDto.getProduceDate())
                .type(goldDto.getType())
                .weight(goldDto.getWeight())
                .deleted(false)
                .workshop(getWorkshop(goldDto.getWorkshopId()))
                .build();
    }

    private Workshop getWorkshop(UUID workshopId) {
        return Workshop.builder()
                .id(workshopId)
                .agent(getWorkshopAgent(getWorkshopAgentDto()))
                .deleted(false)
                .licenseNumber(getEightDigitsRandomString())
                .workshopCode(getEightDigitsRandomString())
                .build();
    }

    private WorkshopAgent getWorkshopAgent(WorkshopAgentDto workshopAgentDto) {
        return WorkshopAgent.builder()
                .id(workshopAgentDto.getId())
                .nationalCode(workshopAgentDto.getNationalCode())
                .name(workshopAgentDto.getName())
                .surname(workshopAgentDto.getSurname())
                .username(workshopAgentDto.getUsername())
                .password(workshopAgentDto.getPassword())
                .deleted(false)
                .build();
    }

    protected Optional<Workshop> getWorkshop() {
        var workshop = getWorkshop(getWorkshopDto().getId());
        return Optional.of(workshop);
    }

    protected String getTrackingCode() {
        return getEightDigitsRandomString();
    }
}
