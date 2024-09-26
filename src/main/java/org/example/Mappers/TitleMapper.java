package org.example.Mappers;

import org.example.Entities.Title;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TitleMapper {
    TitleDTO titleToTitleDTO(Title title);
    Title titleDTOToTitle(TitleDTO titleDTO);
}
