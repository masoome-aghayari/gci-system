package com.gci.agent.certificate.issuance.mapper;

import com.gci.agent.certificate.issuance.model.dto.GoldDto;
import com.gci.agent.certificate.issuance.model.entity.Gold;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface GoldMapper {

    @Mapping(target = "workshopId", ignore = true)
    GoldDto entityToDto(Gold gold);

    @Mapping(target = "workshop", ignore = true)
    Gold dtoToEntity(GoldDto goldDto);
}
