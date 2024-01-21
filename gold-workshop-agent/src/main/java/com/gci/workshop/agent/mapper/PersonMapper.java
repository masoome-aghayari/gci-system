package com.gci.workshop.agent.mapper;


import com.gci.workshop.agent.model.dto.PersonDto;
import com.gci.workshop.agent.model.entity.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PersonMapper {

    PersonDto entityToDto(Person person);

    @Mapping(target = "deleted", ignore = true)
    Person dtoToEntity(PersonDto personDto);
}
