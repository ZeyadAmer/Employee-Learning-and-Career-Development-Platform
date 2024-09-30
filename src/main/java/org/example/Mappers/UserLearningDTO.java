package org.example.Mappers;

import org.example.Entities.LearningTypes;
import org.example.Entities.UserLearning;

import java.util.Date;

public class UserLearningDTO {
    private int id;
    private int userId;
    private LearningTypesDTO learningTypesDTO;
    private String proof;
    private ProofTypesDTO proofTypesDTO;
    private BoosterDTO boosterDTO;
    private Date date;
    private UserLearning.ApprovalStatus approvalStatus;
    private String comment;

    public UserLearningDTO(){}

    public UserLearningDTO(int userId, LearningTypesDTO learningTypesDTO, String proof, ProofTypesDTO proofTypesDTO
    , BoosterDTO boosterDTO, Date date, UserLearning.ApprovalStatus approvalStatus, String comment){
        this.userId = userId;
        this.learningTypesDTO = learningTypesDTO;
        this.proof = proof;
        this.proofTypesDTO = proofTypesDTO;
        this.boosterDTO = boosterDTO;
        this.date = date;
        this.approvalStatus = approvalStatus;
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public LearningTypesDTO getLearningTypesDTO() {
        return learningTypesDTO;
    }

    public void setLearningTypesDTO(LearningTypesDTO learningTypesDTO) {
        this.learningTypesDTO = learningTypesDTO;
    }

    public String getProof() {
        return proof;
    }

    public void setProof(String proof) {
        this.proof = proof;
    }

    public ProofTypesDTO getProofTypesDTO() {
        return proofTypesDTO;
    }

    public void setProofTypesDTO(ProofTypesDTO proofTypesDTO) {
        this.proofTypesDTO = proofTypesDTO;
    }

    public BoosterDTO getBoosterDTO() {
        return boosterDTO;
    }

    public void setBoosterDTO(BoosterDTO boosterDTO) {
        this.boosterDTO = boosterDTO;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public UserLearning.ApprovalStatus getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(UserLearning.ApprovalStatus approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
