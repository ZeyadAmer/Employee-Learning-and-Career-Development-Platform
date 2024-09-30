package org.example.Services;

import org.example.Entities.ProofTypes;
import org.example.Exceptions.ExistsException;
import org.example.Mappers.ProofTypesDTO;
import org.example.Mappers.ProofTypesMapper;
import org.example.Repositories.ProofTypesRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProofTypesService {

    private ProofTypesMapper proofTypesMapper;
    private ProofTypesRepository proofTypesRepository;

    public ProofTypesService(ProofTypesMapper proofTypesMapper, ProofTypesRepository proofTypesRepository){
        this.proofTypesMapper = proofTypesMapper;
        this.proofTypesRepository = proofTypesRepository;
    }

    public void createProofType(ProofTypesDTO proofTypesDTO){
        if(proofTypesRepository.findByProofType(proofTypesDTO.getProofType()).isPresent()){
            throw new ExistsException("Proof Type already exists");
        }
        proofTypesRepository.save(proofTypesMapper.proofTypeaDTOToProofTypes(proofTypesDTO));
    }

    public void deleteProofType(String proofTypeName){
        Optional<ProofTypes> proofType= proofTypesRepository.findByProofType(proofTypeName);
        if(proofType.isEmpty()){
            throw new ExistsException("Proof Type does not exist");
        }
        proofTypesRepository.delete(proofType.get());
    }

    public void updateProofType(String oldName, String newName){
        Optional<ProofTypes> proofType = proofTypesRepository.findByProofType(oldName);
        if(proofType.isEmpty()){
            throw new ExistsException("Proof Type does not exist");
        }

        ProofTypes updatedProofType = proofType.get();
        updatedProofType.setProof_type(newName);
        proofTypesRepository.save(updatedProofType);
    }

    public ProofTypesDTO getProofType(String proofTypeName){
        Optional<ProofTypes> proofType = proofTypesRepository.findByProofType(proofTypeName);
        if(proofType.isEmpty()){
            throw new ExistsException("Proof Type does not exist");
        }
        return proofTypesMapper.proofTypesToProofTypesDTO(proofType.get());
    }

    public List<ProofTypesDTO> getAllProofTypes(){
        List<ProofTypes> proofTypes = proofTypesRepository.findAll();
        return proofTypesMapper.proofTypesListToProofTypesDTOsList(proofTypes);
    }
}
