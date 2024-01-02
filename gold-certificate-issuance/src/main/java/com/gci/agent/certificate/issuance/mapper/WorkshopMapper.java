package com.gci.agent.certificate.issuance.mapper;

import com.gci.agent.certificate.issuance.model.dto.WorkshopDto;
import com.gci.agent.certificate.issuance.model.entity.Workshop;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = WorkshopAgentMapper.class)
public interface WorkshopMapper {

    WorkshopDto entityToDto(Workshop workshop);

    Workshop dtoToEntity(WorkshopDto workshopDto);
}
