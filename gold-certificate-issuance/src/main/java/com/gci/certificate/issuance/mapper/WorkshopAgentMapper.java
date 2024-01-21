package com.gci.certificate.issuance.mapper;

import com.gci.certificate.issuance.model.dto.WorkshopAgentDto;
import com.gci.certificate.issuance.model.entity.WorkshopAgent;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = PersonMapper.class)
public interface WorkshopAgentMapper {

    WorkshopAgentDto entityToDto(WorkshopAgent workshopAgent);

    WorkshopAgent dtoToEntity(WorkshopAgentDto workshopAgentDto);
}
