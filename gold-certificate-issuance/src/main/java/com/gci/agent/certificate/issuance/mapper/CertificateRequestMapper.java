package com.gci.agent.certificate.issuance.mapper;

import com.gci.agent.certificate.issuance.model.dto.CertificateRequestDto;
import com.gci.agent.certificate.issuance.model.entity.CertificateRequest;
import com.gci.agent.certificate.issuance.model.entity.WorkshopAgent;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {GoldMapper.class, WorkshopAgent.class})
public interface CertificateRequestMapper {

    CertificateRequestDto entityToDto(CertificateRequest certificateRequest);

    CertificateRequest dtoToEntity(CertificateRequestDto certificateRequestDto);
}
