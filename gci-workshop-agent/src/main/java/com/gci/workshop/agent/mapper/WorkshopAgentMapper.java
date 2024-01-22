package com.gci.workshop.agent.mapper;

import com.gci.workshop.agent.model.dto.WorkshopAgentDto;
import com.gci.workshop.agent.model.entity.WorkshopAgent;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = PersonMapper.class)
public interface WorkshopAgentMapper {

    WorkshopAgentDto entityToDto(WorkshopAgent workshopAgent);

    WorkshopAgent dtoToEntity(WorkshopAgentDto workshopAgentDto);
}
