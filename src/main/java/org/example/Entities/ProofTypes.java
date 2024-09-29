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
    private String proof_type;
    @OneToMany(mappedBy = "proofType", cascade = CascadeType.ALL)
    private List<UserLearning> userLearnings;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProof_type() {
        return proof_type;
    }

    public void setProof_type(String proof_type) {
        this.proof_type = proof_type;
    }
}
