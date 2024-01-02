package com.gci.agent.certificate.issuance.mapper;

import com.gci.agent.certificate.issuance.model.dto.PersonDto;
import com.gci.agent.certificate.issuance.model.entity.Person;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PersonMapper {

    PersonDto entityToDto(Person person);

    Person dtoToEntity(PersonDto personDto);
}
