package org.example.Entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "proof_types")
public class ProofTypes {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private int id;
    private String proofType;
    @OneToMany(mappedBy = "proofType", cascade = CascadeType.ALL)
    private List<UserLearning> userLearnings;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProof_type() {
        return proofType;
    }

    public void setProof_type(String proof_type) {
        this.proofType = proof_type;
    }
}
