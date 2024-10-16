package org.example.Mappers;


import org.example.Entities.Booster;
import org.example.Entities.LearningTypes;
import org.example.Entities.ProofTypes;
import org.example.Entities.UserLearning;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserLearningMapper {

    // Single object mapping with nested DTOs
    @Mapping(source = "learningType", target = "learningTypesDTO")
    @Mapping(source = "proofType", target = "proofTypesDTO")
    @Mapping(source = "booster", target = "boosterDTO")
    UserLearningDTO userLearningToUserLearningDTO(UserLearning userLearning);

    @Mapping(source = "learningTypesDTO", target = "learningType")
    @Mapping(source = "proofTypesDTO", target = "proofType")
    @Mapping(source = "boosterDTO", target = "booster")
    UserLearning userLearningDTOToUserLearning(UserLearningDTO userLearningDTO);


    // List mapping for UserLearning -> UserLearningDTO
    List<UserLearningDTO> userLearningsListToUserLearningsDTOsList(List<UserLearning> userLearnings);

    // List mapping for UserLearningDTO -> UserLearning
    List<UserLearning> userLearningDTOsListToUserLearningsList(List<UserLearningDTO> userLearningDTOS);

    // Nested mappings for DTO conversions
    BoosterDTO toBoosterDTO(Booster booster);
    Booster toBooster(BoosterDTO boosterDTO);

    LearningTypesDTO toLearningTypesDTO(LearningTypes learningType);
    LearningTypes toLearningTypes(LearningTypesDTO learningTypesDTO);

    ProofTypesDTO toProofTypesDTO(ProofTypes proofType);
    ProofTypes toProofTypes(ProofTypesDTO proofTypesDTO);
}



