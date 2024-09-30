package org.example.Entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "user_learnings")
public class UserLearning {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private int id;

        @Column(nullable = false)
        private int userId; // reference userid in UsersDB

        @ManyToOne
        @JoinColumn(name = "learning_id", nullable = false)
        private LearningTypes learningType;

        private String proof;

        @ManyToOne
        @JoinColumn(name = "proof_id", nullable = false)
        private ProofTypes proofType;

        @ManyToOne
        @JoinColumn(name = "booster_id", nullable = false)
        private Booster booster;

        @Temporal(TemporalType.DATE)
        private Date date;

        @Enumerated(EnumType.STRING)
        private ApprovalStatus approvalStatus;

        private String comment;

        public enum ApprovalStatus {
            PENDING, APPROVED, REJECTED
        }

        public UserLearning(){}

        public UserLearning(String proof, Date date, ApprovalStatus approvalStatus, String comment){
                this.proof = proof;
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

        public LearningTypes getLearningType() {
                return learningType;
        }

        public void setLearningType(LearningTypes learningType) {
                this.learningType = learningType;
        }

        public String getProof() {
                return proof;
        }

        public void setProof(String proof) {
                this.proof = proof;
        }

        public ProofTypes getProofType() {
                return proofType;
        }

        public void setProofType(ProofTypes proofType) {
                this.proofType = proofType;
        }

        public Booster getBooster() {
                return booster;
        }

        public void setBooster(Booster booster) {
                this.booster = booster;
        }

        public Date getDate() {
                return date;
        }

        public void setDate(Date date) {
                this.date = date;
        }

        public ApprovalStatus getApprovalStatus() {
                return approvalStatus;
        }

        public void setApprovalStatus(ApprovalStatus approvalStatus) {
                this.approvalStatus = approvalStatus;
        }

        public String getComment() {
                return comment;
        }

        public void setComment(String comment) {
                this.comment = comment;
        }
}
