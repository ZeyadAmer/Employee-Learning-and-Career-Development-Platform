package org.example.Mappers;

import org.example.Entities.Title;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TitleMapper {
    TitleDTO titleToTitleDTO(Title title);
    Title titleDTOToTitle(TitleDTO titleDTO);
    List<TitleDTO> titleListToTitleDTOList(List<Title> titleList);
    List<Title> titleDTOListToTitleList(List<TitleDTO> titleDTOList);
}
