package org.example.Mappers;

public class ProofTypesDTO {
    private int id;
    private String proofType;

    public ProofTypesDTO(){}

    public ProofTypesDTO(String proofType){
        this.proofType = proofType;
    }

    public String getProofType() {
        return proofType;
    }

    public void setProofType(String proofType) {
        this.proofType = proofType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
