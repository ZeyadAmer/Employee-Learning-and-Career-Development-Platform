package org.example.Mappers;

public class ProofTypesDTO {
    private int id;
    private String proofType; // Change to camelCase to match Java conventions

    // Constructor
    public ProofTypesDTO() {}

    public ProofTypesDTO(String proofType) {
        this.proofType = proofType; // Match variable name
    }

    // Getters and Setters
    public String getProofType() {
        return proofType; // Use updated field name
    }

    public void setProofType(String proofType) {
        this.proofType = proofType; // Use updated field name
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
