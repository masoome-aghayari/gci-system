package com.gci.agent.certificate.issuance.mapper;

import com.gci.agent.certificate.issuance.model.dto.PersonDto;
import com.gci.agent.certificate.issuance.model.entity.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PersonMapper {


    PersonDto entityToDto(Person person);

    @Mapping(target = "deleted", ignore = true)
    Person dtoToEntity(PersonDto personDto);
}
