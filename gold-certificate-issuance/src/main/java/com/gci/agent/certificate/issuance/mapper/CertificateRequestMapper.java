package com.gci.agent.certificate.issuance.mapper;

import com.gci.agent.certificate.issuance.model.dto.CertificateRequestDto;
import com.gci.agent.certificate.issuance.model.entity.CertificateRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {GoldMapper.class})
public interface CertificateRequestMapper {

    @Mapping(target = "agentId", ignore = true)
    CertificateRequestDto entityToDto(CertificateRequest entity);

    CertificateRequest dtoToEntity(CertificateRequestDto dto);
}
