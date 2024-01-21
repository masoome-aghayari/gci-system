package com.gci.certificate.issuance.mapper;

import com.gci.certificate.issuance.model.dto.WorkshopDto;
import com.gci.certificate.issuance.model.entity.Workshop;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = WorkshopAgentMapper.class)
public interface WorkshopMapper {

    @Mapping(target = "agentId", ignore = true)
    WorkshopDto entityToDto(Workshop workshop);

    @Mappings({
            @Mapping(target = "agent", ignore = true),
            @Mapping(target = "deleted", ignore = true),
    })
    Workshop dtoToEntity(WorkshopDto workshopDto);
}
