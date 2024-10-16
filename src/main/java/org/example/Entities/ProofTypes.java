package org.example.Entities;

import javax.persistence.*;
import java.util.List;
@Entity
@Table(name = "proof_types")
public class ProofTypes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "proof_type", nullable = false) // Ensure nullable = false if the column shouldn't be null
    private String proofType;

    // One-to-many relationship with UserLearning (optional part of your entity)
    @OneToMany(mappedBy = "proofType", cascade = CascadeType.ALL)
    private List<UserLearning> userLearnings;

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProofType() {
        return proofType;
    }

    public void setProofType(String proofType) {
        this.proofType = proofType;
    }
}
