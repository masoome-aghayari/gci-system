package com.gci.agent.certificate.issuance.mapper;

import com.gci.agent.certificate.issuance.model.dto.GoldDto;
import com.gci.agent.certificate.issuance.model.entity.Gold;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {WorkshopMapper.class})
public interface GoldMapper {

    GoldDto entityToDto(Gold gold);

    Gold dtoToEntity(GoldDto goldDto);
}
