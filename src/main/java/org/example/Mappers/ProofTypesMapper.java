package org.example.Mappers;


import org.example.Entities.ProofTypes;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProofTypesMapper {
    ProofTypesDTO proofTypesToProofTypesDTO(ProofTypes proofTypes);
    ProofTypes proofTypeaDTOToProofTypes(ProofTypesDTO proofTypesDTO);
    List<ProofTypesDTO> proofTypesListToProofTypesDTOsList(List<ProofTypes> proofTypes);
    List<ProofTypes> proofTypesDTOsListToProofTypesList(List<ProofTypesDTO> proofTypesDTOS);
}
